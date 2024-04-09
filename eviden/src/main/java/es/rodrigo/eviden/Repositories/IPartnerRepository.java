package es.rodrigo.eviden.Repositories;

import es.rodrigo.eviden.Models.Partner;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Transactional
public interface IPartnerRepository extends JpaRepository<Partner,Integer> {
    Partner findByUsername(String username);
}
