package teste.vr.server.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import teste.vr.server.dtos.ClientInfoDTO;
import teste.vr.server.dtos.request.ShoppingItemsRequestDTO;
import teste.vr.server.dtos.response.ShoppingItemsResponseDTO;
import teste.vr.server.entities.ShoppingItems;
import teste.vr.server.exception.runtime.CreditLimitExceededException;
import teste.vr.server.exception.runtime.DuplicateProductAttemptException;
import teste.vr.server.exception.runtime.ObjectNotFoundException;
import teste.vr.server.exception.runtime.PersistFailedException;
import teste.vr.server.repositories.ShoppingItemsRepositoty;
import teste.vr.server.services.OrderService;
import teste.vr.server.services.ShoppingItemsService;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class ShoppingItemsServiceImpl implements ShoppingItemsService {

    private final OrderService orderService;
    private final ProductServiceImpl productService;
    private final ShoppingItemsRepositoty shoppingItemsRepositoty;

    @Override
    public Page<ShoppingItemsResponseDTO> findAllByOrderId(Long orderId, Pageable pageable) {

        Page<ShoppingItems> shoppingItems = this.shoppingItemsRepositoty.findAllByOrderId(orderId, pageable);

        return shoppingItems.map(ShoppingItemsResponseDTO::new);
    }

    @Override
    public ShoppingItemsResponseDTO save(ShoppingItemsRequestDTO shoppingItemsDTO) {

        this.validateProduct(shoppingItemsDTO);

        ShoppingItems shoppingItems = shoppingItemsDTO.converterToEntity();

        this.calculateSubtotal(shoppingItems);

        this.limitValidate(shoppingItems.getOrder().getId(), shoppingItems.getSubtotal());

        try {
            ShoppingItems shoppingItemsPersisted = this.shoppingItemsRepositoty.save(shoppingItems);
            return new ShoppingItemsResponseDTO(shoppingItemsPersisted);
        } catch (Exception e) {
            throw new PersistFailedException("Shopping not saved");
        }
    }

    private void validateProduct(ShoppingItemsRequestDTO shoppingItemsDTO) {
        if (this.shoppingItemsRepositoty.existsByOrderIdAndProductId(
                shoppingItemsDTO.getOrderId(),
                shoppingItemsDTO.getProductId())) {

            throw new DuplicateProductAttemptException("Product already exists in this order");
        }
    }

    private void limitValidate(Long orderId, BigDecimal subtotal) {

        BigDecimal totalValueByOrder = this.shoppingItemsRepositoty.findTotalByOrderId(orderId);

        if (totalValueByOrder == null) totalValueByOrder = BigDecimal.ZERO;

        BigDecimal newTotalValue = totalValueByOrder.add(subtotal);

        ClientInfoDTO clientInfoDTO = this.orderService.findClientInfoByOrderId(orderId);
        BigDecimal valueExceeded = newTotalValue.subtract(clientInfoDTO.getCreditLimit());

        if (newTotalValue.compareTo(clientInfoDTO.getCreditLimit()) > 0) {

            LocalDate expirationDate = this.expirationDate(clientInfoDTO.getDueDay());
            throw new CreditLimitExceededException(
                    clientInfoDTO.getCreditLimit(),
                    newTotalValue,
                    valueExceeded,
                    expirationDate,
                    "Your credit limit has been exceeded. Please, contact your administrator"
            );
        }
    }

    private LocalDate expirationDate(Integer dueDay) {

        LocalDate now = LocalDate.now();

        if (dueDay < now.getDayOfMonth()) {
            LocalDate nextMonth = now.plusMonths(1);
            if (dueDay > nextMonth.lengthOfMonth()) dueDay = nextMonth.lengthOfMonth();

            return LocalDate.of(nextMonth.getYear(), nextMonth.getMonth(), dueDay);
        } else {
            if (dueDay > now.lengthOfMonth()) dueDay = now.lengthOfMonth();

            return LocalDate.of(now.getYear(), now.getMonth(), dueDay);
        }
    }

    private void calculateSubtotal(ShoppingItems shoppingItems) {
        if (shoppingItems.getProduct() == null || shoppingItems.getQuantity() == null) {
            throw new ObjectNotFoundException("Product or quantity not found");
        } else {

            BigDecimal price = this.productService.findPriceByProductId(shoppingItems.getProduct().getId());
            BigDecimal qty = new BigDecimal(shoppingItems.getQuantity());
            shoppingItems.setSubtotal(price.multiply(qty));
        }
    }

}
