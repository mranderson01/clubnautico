package es.rodrigo.eviden.Repositories;

import java.util.Optional;

import es.rodrigo.eviden.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;




@Repository
public interface IUserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);

}
