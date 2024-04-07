package es.rodrigo.eviden.security.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import es.rodrigo.eviden.security.Models.User;

@Repository
@Transactional
public interface IUserRepository extends JpaRepository<User,Integer> {
    User findByUsername(String username);
}
