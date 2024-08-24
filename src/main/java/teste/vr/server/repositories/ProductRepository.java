package teste.vr.server.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import teste.vr.server.entities.Products;

import java.math.BigDecimal;

@Repository
public interface ProductRepository extends JpaRepository<Products, Long> {

    Products findByActiveIsTrueAndId(Long id);
    Page<Products> findAllByActiveIsTrue(Pageable pageable);
    @Modifying
    @Transactional
    @Query(value = "UPDATE Products p SET p.active = false WHERE p.id = :id", nativeQuery = true)
    void updateActiveFalseById(Long id);
    @Query(value = "SELECT price FROM Products p WHERE p.id = :productId", nativeQuery = true)
    BigDecimal findPriceByProductId(Long productId);
}
