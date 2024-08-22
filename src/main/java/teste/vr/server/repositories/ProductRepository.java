package teste.vr.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import teste.vr.server.entities.Products;

@Repository
public interface ProductRepository extends JpaRepository<Products, Long> {
}
