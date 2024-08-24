package teste.vr.server.dtos.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import teste.vr.server.entities.Order;
import teste.vr.server.entities.ShoppingItems;

import java.math.BigDecimal;

@Getter
@Setter
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderResponseDTO {

    private Long orderId;
    private Long clientId;
    private BigDecimal totalValue;

    public OrderResponseDTO(Order order) {
        this.orderId = order.getId();
        this.clientId = order.getClient().getId();
        this.totalValue = this.calculeTotalValue(order);
    }

    private BigDecimal calculeTotalValue(Order order) {

        BigDecimal totalValue = new BigDecimal(0);
        for (ShoppingItems item : order.getItems()) {
            totalValue = totalValue.add(item.getSubtotal());
        }
        return totalValue;
    }
}
