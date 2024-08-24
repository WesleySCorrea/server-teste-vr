package teste.vr.server.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import teste.vr.server.entities.ShoppingItems;
import teste.vr.server.entities.ShoppingItemsId;

import java.math.BigDecimal;

@Repository
public interface ShoppingItemsRepositoty extends JpaRepository<ShoppingItems, ShoppingItemsId> {

    boolean existsByOrderIdAndProductId(Long orderId, Long productId);
    Page<ShoppingItems> findAllByOrderId(Long orderId, Pageable pageable);
    @Query(value = "SELECT SUM(subtotal) FROM shopping_items s WHERE s.order_id = :orderId", nativeQuery = true)
    BigDecimal findTotalByOrderId(Long orderId);
}
