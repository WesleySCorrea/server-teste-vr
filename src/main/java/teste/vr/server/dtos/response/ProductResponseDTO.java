package teste.vr.server.dtos.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import teste.vr.server.entities.Products;

import java.math.BigDecimal;

@Getter
@Setter
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductResponseDTO {

    private Long id;
    private String title;
    private String description;
    private BigDecimal price;
    private Boolean active;

    public ProductResponseDTO (Products product) {
        this.id = product.getId();
        this.title = product.getTitle();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.active = product.getActive();
    }
}
