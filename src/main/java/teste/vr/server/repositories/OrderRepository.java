package teste.vr.server.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import teste.vr.server.entities.Order;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    Boolean existsOrderByIdAndFinishedIsTrue(Long id);
    Page<Order> findAllOrdersByClientId(Long clientId, Pageable pageable);
    @Query(value = "SELECT * FROM orders JOIN shopping_items ON orders.id = shopping_items.order_id WHERE shopping_items.product_id = :productId", nativeQuery = true)
    Page<Order> findAllOrdersByProductId(Long productId, Pageable pageable);
    @Modifying
    @Transactional
    @Query(value = "UPDATE Orders SET total_value = :totalValue WHERE id = :orderId", nativeQuery = true)
    void updateTotalValueByOrderId(Long orderId, BigDecimal totalValue);
    @Modifying
    @Transactional
    @Query(value = "UPDATE Orders SET total_value = :totalValue, finished = true WHERE id = :orderId", nativeQuery = true)
    void updateTotalValueAndFinishByOrderId(Long orderId, BigDecimal totalValue);
    @Query(value = "SELECT c.credit_limit, c.due_day FROM Orders o JOIN Clients c ON o.client_id = c.id WHERE o.id = :orderId", nativeQuery = true)
    List<Object[]> findClientInfoByOrderId(Long orderId);
    @Query(value = "SELECT SUM(subtotal), finished, client_id FROM orders LEFT JOIN shopping_items ON orders.id = shopping_items.order_id WHERE orders.id = :orderId GROUP BY orders.finished, orders.client_id", nativeQuery = true)
    List<Object[]> findOrderInfoByOrderId(Long orderId);
}