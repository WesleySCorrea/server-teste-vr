package teste.vr.server.entities;

import jakarta.persistence.Embeddable;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class ShoppingItemsId {

    private Long orderId;
    private Long productId;
}
