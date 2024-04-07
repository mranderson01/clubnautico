package es.rodrigo.eviden.Repositories;

import es.rodrigo.eviden.Models.Partner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPartnerRepository extends JpaRepository<Integer, Partner> {
}
