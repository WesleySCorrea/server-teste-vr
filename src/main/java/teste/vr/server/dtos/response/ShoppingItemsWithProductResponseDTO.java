package teste.vr.server.dtos.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ShoppingItemsWithProductResponseDTO {
    private Long productId;
    private String title;
    private BigDecimal price;
    private Integer quantity;
    private BigDecimal subtotal;

    public ShoppingItemsWithProductResponseDTO(Object[] obj) {
        this.productId = (Long) obj[0];
        this.title = (String) obj[1];
        this.quantity = (Integer) obj[2];
        this.price = (BigDecimal) obj[3];
        this.subtotal = (BigDecimal) obj[4];
    }
}
