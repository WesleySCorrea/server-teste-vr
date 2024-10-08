package teste.vr.server.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import teste.vr.server.dtos.ClientInfoDTO;
import teste.vr.server.dtos.request.ClientRequestDTO;
import teste.vr.server.dtos.request.OrderRequestDTO;
import teste.vr.server.dtos.response.ClientResponseDTO;
import teste.vr.server.dtos.response.OrderResponseDTO;
import teste.vr.server.entities.Clients;
import teste.vr.server.entities.Order;
import teste.vr.server.exception.runtime.ObjectNotFoundException;
import teste.vr.server.exception.runtime.OrderIsEmptyException;
import teste.vr.server.exception.runtime.OrderIsFinishedException;
import teste.vr.server.exception.runtime.PersistFailedException;
import teste.vr.server.repositories.OrderRepository;
import teste.vr.server.repositories.ShoppingItemsRepositoty;
import teste.vr.server.services.ClientService;
import teste.vr.server.services.OrderService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final ClientService clientService;
    private final OrderRepository orderRepository;
    private final ShoppingItemsRepositoty shoppingItemsRepositoty;

    @Override
    public Page<OrderResponseDTO> findAllOrders(Pageable pageable) {

        Page<Order> orders = orderRepository.findAll(pageable);

        return orders.map(OrderResponseDTO::new);
    }

    @Override
    public Page<OrderResponseDTO> findAllOrdersByClientId(Long clientId, Pageable pageable) {

        Page<Order> orders = orderRepository.findAllOrdersByClientId(clientId, pageable);

        return orders.map(OrderResponseDTO::new);
    }

    @Override
    public Page<OrderResponseDTO> findAllOrdersByProductId(Long productId, Pageable pageable) {

        Page<Order> orders = orderRepository.findAllOrdersByProductId(productId,pageable);

        return orders.map(OrderResponseDTO::new);
    }

    @Override
    public OrderResponseDTO findOrderById(Long id) {

        Optional<Order> optionalOrder = orderRepository.findById(id);

        OrderResponseDTO orderResponseDTO = optionalOrder.map(OrderResponseDTO::new).orElse(null);

        if (!Objects.equals(optionalOrder.get().getTotalValue(), orderResponseDTO.getTotalValue())) {
            this.orderRepository.updateTotalValueByOrderId(orderResponseDTO.getOrderId(), orderResponseDTO.getTotalValue());
        }

        return optionalOrder.map(OrderResponseDTO::new).orElse(null);
    }

    @Override
    public OrderResponseDTO saveOrder(OrderRequestDTO orderRequestDTO) {

        Order order = new Order();
        Clients client = this.clientService.findByIdAndActiveIsTrue(orderRequestDTO.getClientId());
        if (client == null) throw new ObjectNotFoundException("Client not found or inactive");

        order.setClient(client);

        try {
            Order orderPersisted = this.orderRepository.save(order);
            return new OrderResponseDTO(orderPersisted);
        } catch (Exception e) {
            throw new PersistFailedException("Order not saved");
        }
    }

    @Override
    public ClientInfoDTO findClientInfoByOrderId(Long orderId) {

        List<Object[]> clientInfo = this.orderRepository.findClientInfoByOrderId(orderId);

        BigDecimal creditLimit = (BigDecimal) clientInfo.get(0)[0];
        Integer dueDay = (Integer) clientInfo.get(0)[1];

        ClientInfoDTO clientInfoDTO = new ClientInfoDTO();
        clientInfoDTO.setCreditLimit(creditLimit);
        clientInfoDTO.setDueDay(dueDay);

        return clientInfoDTO;
    }

    @Override
    public OrderResponseDTO confirmOrder(Long id) {

        List<Object[]> orderInfo = this.orderRepository.findOrderInfoByOrderId(id);

        BigDecimal totalValueOrder = (BigDecimal) orderInfo.get(0)[0];
        Boolean finished = (Boolean) orderInfo.get(0)[1];
        Long clientId = (Long) orderInfo.get(0)[2];

        if (finished)  {
            throw new OrderIsFinishedException("Order already finished");
        } else  if (totalValueOrder == null) {
            throw new OrderIsEmptyException("Your order is empty");
        }

        this.updateCreditLimitByClientId(clientId, totalValueOrder);
        this.orderRepository.updateTotalValueAndFinishByOrderId(id, totalValueOrder);

        return this.findOrderById(id);
    }

    @Override
    public void deleteOrder(Long id) {

        if (this.orderRepository.existsOrderByIdAndFinishedIsTrue(id)) {
            throw new OrderIsFinishedException("Order already finished");
        } if (!this.orderRepository.existsById(id)) {
            throw new ObjectNotFoundException("Order not found");
        }
        this.orderRepository.deleteById(id);
    }

    private void updateCreditLimitByClientId(Long clientId, BigDecimal totalValue) {

        ClientResponseDTO clientIdPersisted = this.clientService.findClientById(clientId);

        BigDecimal newCreditLimit = clientIdPersisted.getCreditLimit().subtract(totalValue);
        ClientRequestDTO clientRequestDTO = new ClientRequestDTO();
        clientRequestDTO.setCreditLimit(newCreditLimit);
        this.clientService.updateClient(clientId, clientRequestDTO);
    }

}
