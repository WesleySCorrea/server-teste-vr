package teste.vr.server.services.impl;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import teste.vr.server.dtos.request.ProductRequestDTO;
import teste.vr.server.dtos.response.ProductResponseDTO;
import teste.vr.server.entities.Products;
import teste.vr.server.repositories.ProductRepository;
import teste.vr.server.services.ProductService;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Page<ProductResponseDTO> findAllProducts(Pageable pageable) {

        Page<Products> products = productRepository.findAll(pageable);

        return products.map(ProductResponseDTO::new);
    }

    @Override
    public ProductResponseDTO findProductById(Long id) {

        Optional<Products> optionalProducts = productRepository.findById(id);

        return optionalProducts.map(ProductResponseDTO::new).orElse(null);
    }

    @Override
    public ProductResponseDTO saveProduct(ProductRequestDTO productRequestDTO) {

        Products products = productRequestDTO.converterDTO();

        try {
            Products productPersisted = this.productRepository.save(products);

            return new ProductResponseDTO(productPersisted);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ProductResponseDTO updateProduct(Long id, ProductRequestDTO productRequestDTO) {

        var productPersisted = this.findProductById(id);

        if (productPersisted == null) {
            throw new RuntimeException("Product not found");
        }

        Products products = productRequestDTO.converterPersisted(productPersisted);

        Products productUpdate = this.productRepository.save(products);

        return new ProductResponseDTO(productUpdate);
    }

    @Override
    public void deleteProduct(Long id) {

        var product = this.findProductById(id);

        if (product == null) {
            throw new RuntimeException("Product not found");
        }
        this.productRepository.deleteById(id);
    }
}
