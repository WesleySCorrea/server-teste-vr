package teste.vr.server.repositories;

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
}