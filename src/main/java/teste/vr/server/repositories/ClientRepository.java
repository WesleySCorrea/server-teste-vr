package teste.vr.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import teste.vr.server.entities.Clients;

@Repository
public interface ClientRepository extends JpaRepository<Clients, Long> {
}
