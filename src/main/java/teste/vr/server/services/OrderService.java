package teste.vr.server.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import teste.vr.server.dtos.ClientInfoDTO;
import teste.vr.server.dtos.request.OrderRequestDTO;
import teste.vr.server.dtos.response.OrderResponseDTO;

public interface OrderService {

    Page<OrderResponseDTO> findAllOrders(Pageable pageable);
    Page<OrderResponseDTO> findAllOrdersByClientId(Long clientId, Pageable pageable);
    Page<OrderResponseDTO> findAllOrdersByProductId(Long productId, Pageable pageable);
    OrderResponseDTO findOrderById(Long id);
    OrderResponseDTO saveOrder(OrderRequestDTO orderRequestDTO);
    ClientInfoDTO findClientInfoByOrderId(Long orderId);
    OrderResponseDTO confirmOrder(Long id);
    void deleteOrder(Long id);
}
