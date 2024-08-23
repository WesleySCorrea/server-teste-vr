package teste.vr.server.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import teste.vr.server.dtos.request.ProductRequestDTO;
import teste.vr.server.dtos.response.ProductResponseDTO;

public interface ProductService {
    
    Page<ProductResponseDTO> findAllProducts(Pageable pageable);
    ProductResponseDTO findProductById(Long id);
    ProductResponseDTO saveProduct(ProductRequestDTO productRequestDTO);
    ProductResponseDTO updateProduct(Long id, ProductRequestDTO productRequestDTO);
    void deleteProduct(Long id);
}
