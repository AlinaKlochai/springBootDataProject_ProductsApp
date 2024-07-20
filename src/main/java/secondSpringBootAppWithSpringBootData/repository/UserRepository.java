package secondSpringBootAppWithSpringBootData.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import secondSpringBootAppWithSpringBootData.entity.User;


import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByEmail(String email);
    Optional<User> findById(Long userId);
    Optional<User> findUserByName(String name);
}
