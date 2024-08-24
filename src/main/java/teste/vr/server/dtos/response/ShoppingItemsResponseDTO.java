package teste.vr.server.dtos.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import teste.vr.server.entities.ShoppingItems;
import teste.vr.server.entities.ShoppingItemsId;

import java.math.BigDecimal;

@Getter
@Setter
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ShoppingItemsResponseDTO {

    private ShoppingItemsId id;
    private Integer quantity;
    private BigDecimal subtotal;

    public ShoppingItemsResponseDTO(ShoppingItems shoppingItems) {
        this.id = shoppingItems.getId();
        this.quantity = shoppingItems.getQuantity();
        this.subtotal = shoppingItems.getSubtotal();
    }
}
