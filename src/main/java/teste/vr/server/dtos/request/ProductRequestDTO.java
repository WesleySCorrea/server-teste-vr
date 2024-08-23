package teste.vr.server.dtos.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import teste.vr.server.dtos.response.ProductResponseDTO;
import teste.vr.server.entities.Products;

import java.math.BigDecimal;

@Getter
@Setter
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductRequestDTO {

    private Long id;
    private String title;
    private String description;
    private BigDecimal price;

    public Products converterDTO() {
        Products products = new Products();
        products.setTitle(this.getTitle());
        products.setDescription(this.getDescription());
        products.setPrice(this.getPrice());
        return products;
    }

    public Products converterPersisted(ProductResponseDTO productResponseDTO) {
        Products products = new Products();
        products.setId(productResponseDTO.getId());

        if (this.getTitle() != null) {
            products.setTitle(this.getTitle());
        } else {
            products.setTitle(productResponseDTO.getTitle());
        }

        if (this.getDescription() != null) {
            products.setDescription(this.getDescription());
        } else {
            products.setDescription(productResponseDTO.getDescription());
        }

        if (this.getPrice() != null) {
            products.setPrice(this.getPrice());
        } else {
            products.setPrice(productResponseDTO.getPrice());
        }

        return products;
    }
}
