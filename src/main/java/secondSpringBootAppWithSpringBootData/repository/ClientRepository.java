package secondSpringBootAppWithSpringBootData.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import secondSpringBootAppWithSpringBootData.entity.Client;
import secondSpringBootAppWithSpringBootData.entity.Product;


import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
    List<Client> findAllByLastName(String lastName);
    Optional<Client> findAllByEmail (String email);
    Optional<Client> findById(Integer clientId);
}
