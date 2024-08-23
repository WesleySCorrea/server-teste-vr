package teste.vr.server.controllers;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import teste.vr.server.dtos.request.ProductRequestDTO;
import teste.vr.server.dtos.response.ProductResponseDTO;
import teste.vr.server.services.ProductService;

import java.net.URI;

@RestController
@AllArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<Page<ProductResponseDTO>> findAllProducts(Pageable pageable) {

        Page<ProductResponseDTO> products = this.productService.findAllProducts(pageable);

        return ResponseEntity.ok().body(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> findByProductId(@PathVariable Long id) {

        ProductResponseDTO product = this.productService.findProductById(id);

        return ResponseEntity.ok().body(product);
    }

    @PostMapping
    public ResponseEntity<ProductResponseDTO> saveClient(@RequestBody ProductRequestDTO productRequestDTO) {

        ProductResponseDTO productPersisted = this.productService.saveProduct(productRequestDTO);
        URI location = URI.create("/api/your-entities/" + productPersisted.getId());

        return ResponseEntity.created(location).body(productPersisted);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> updateProduct(@PathVariable Long id,@RequestBody ProductRequestDTO productRequestDTO) {

        ProductResponseDTO productPersisted = this.productService.updateProduct(id, productRequestDTO);

        return ResponseEntity.ok().body(productPersisted);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {

        this.productService.deleteProduct(id);

        return ResponseEntity.noContent().build();
    }
}
