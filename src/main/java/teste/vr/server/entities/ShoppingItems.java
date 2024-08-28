package teste.vr.server.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "shopping_items")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ShoppingItems {

    @EmbeddedId
    private ShoppingItemsId id;
    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name = "order_id")
    private Order order;
    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private Products product;
    @Column(name = "quantity")
    private Integer quantity;
    @Column(name = "subtotal", precision = 10, scale = 2)
    private BigDecimal subtotal;
}
