package teste.vr.server.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import teste.vr.server.dtos.request.ProductRequestDTO;
import teste.vr.server.dtos.response.ProductResponseDTO;
import teste.vr.server.entities.Products;
import teste.vr.server.exception.runtime.ObjectNotFoundException;
import teste.vr.server.exception.runtime.PersistFailedException;
import teste.vr.server.repositories.ProductRepository;
import teste.vr.server.services.ProductService;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Page<ProductResponseDTO> findAllProducts(Pageable pageable) {

        Page<Products> products = productRepository.findAllByActiveIsTrue(pageable);

        return products.map(ProductResponseDTO::new);
    }

    @Override
    public ProductResponseDTO findProductById(Long id) {

        Products products = productRepository.findByActiveIsTrueAndId(id);

        if (products == null) throw new ObjectNotFoundException("Product not found");

        return new ProductResponseDTO(products);
    }

    @Override
    public ProductResponseDTO saveProduct(ProductRequestDTO productRequestDTO) {

        Products products = productRequestDTO.converterDTO();

        try {
            Products productPersisted = this.productRepository.save(products);

            return new ProductResponseDTO(productPersisted);
        } catch (Exception e) {
            throw new PersistFailedException("Product not found");
        }
    }

    @Override
    public ProductResponseDTO updateProduct(Long id, ProductRequestDTO productRequestDTO) {

        var productPersisted = this.findProductById(id);

        if (productPersisted == null) throw new ObjectNotFoundException("Product not found");

        Products products = productRequestDTO.converterPersisted(productPersisted);

        Products productUpdate = this.productRepository.save(products);

        return new ProductResponseDTO(productUpdate);
    }

    @Override
    public void deleteProduct(Long id) {

        var product = this.productRepository.findByActiveIsTrueAndId(id);

        if (product == null) throw new ObjectNotFoundException("Product not found");

        this.productRepository.updateActiveFalseById(id);
    }

    @Override
    public BigDecimal findPriceByProductId(Long productId) {

        return this.productRepository.findPriceByProductId(productId);
    }
}
