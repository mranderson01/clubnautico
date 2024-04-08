package es.rodrigo.eviden.Repositories;

import es.rodrigo.eviden.Models.Partner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IPartnerRepository extends JpaRepository<Partner,Integer> {
    Partner findByUsername(String username);
}
