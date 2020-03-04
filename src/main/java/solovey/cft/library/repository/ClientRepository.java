package solovey.cft.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import solovey.cft.library.model.Client;

import java.util.List;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {

    Optional<Client> findByEmail(String email);

    Client findByPassport(Long number);

    List<Client> findAll();

}
