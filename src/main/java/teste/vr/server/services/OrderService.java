package teste.vr.server.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import teste.vr.server.dtos.ClientInfoDTO;
import teste.vr.server.dtos.request.OrderRequestDTO;
import teste.vr.server.dtos.response.OrderResponseDTO;

import java.math.BigDecimal;

public interface OrderService {

    Page<OrderResponseDTO> findAllOrders(Pageable pageable);
    OrderResponseDTO findOrderById(Long id);
    OrderResponseDTO saveOrder(OrderRequestDTO orderRequestDTO);
//    OrderResponseDTO updateOrder(Long id, OrderRequestDTO orderRequestDTO);
//    void deleteOrder(Long id);
    ClientInfoDTO findClientInfoByOrderId(Long orderId);
    OrderResponseDTO confirmOrder(Long id);
}
