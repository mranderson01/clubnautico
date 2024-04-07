package es.rodrigo.eviden.security.repositories;

import es.rodrigo.eviden.security.Models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface IRoleRepository extends JpaRepository<Role,Integer> {
    Role findRoleByName(String roleAdmin);
}
