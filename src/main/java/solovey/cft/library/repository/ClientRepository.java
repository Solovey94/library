package solovey.cft.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import solovey.cft.library.model.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
    Client getClientByPassport(Long number);
}
