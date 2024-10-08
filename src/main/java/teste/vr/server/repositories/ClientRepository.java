package teste.vr.server.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import teste.vr.server.entities.Clients;

@Repository
public interface ClientRepository extends JpaRepository<Clients, Long> {

    Page<Clients> findAllByActiveIsTrue(Pageable pageable);
    Clients findByActiveIsTrueAndId(Long id);
    @Modifying
    @Transactional
    @Query(value = "UPDATE Clients SET active = false WHERE id = :id", nativeQuery = true)
    void updateActiveFalseById(Long id);
}
