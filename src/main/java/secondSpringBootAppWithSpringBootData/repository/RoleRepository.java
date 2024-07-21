package secondSpringBootAppWithSpringBootData.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import secondSpringBootAppWithSpringBootData.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRole(String role);
}
