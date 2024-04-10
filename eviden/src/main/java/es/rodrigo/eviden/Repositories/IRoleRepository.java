package es.rodrigo.eviden.Repositories;

import java.util.Optional;

import es.rodrigo.eviden.Models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface IRoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByNombre(String nombreRole);
    Boolean existsByNombre(String nombre);
}
