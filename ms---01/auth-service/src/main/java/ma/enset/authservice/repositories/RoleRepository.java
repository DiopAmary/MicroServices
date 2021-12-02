package ma.enset.authservice.repositories;

import ma.enset.authservice.entities.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    RoleEntity findRoleEntityByRole(String role);
}
