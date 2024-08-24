package teste.vr.server.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import teste.vr.server.dtos.request.ShoppingItemsRequestDTO;
import teste.vr.server.dtos.response.ShoppingItemsResponseDTO;

public interface ShoppingItemsService {

    Page<ShoppingItemsResponseDTO> findAllByOrderId(Long orderId, Pageable pageable);
    ShoppingItemsResponseDTO save(ShoppingItemsRequestDTO shoppingItemsDTO);
}
