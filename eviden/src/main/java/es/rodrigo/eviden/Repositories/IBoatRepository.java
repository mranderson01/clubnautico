package es.rodrigo.eviden.Repositories;

import es.rodrigo.eviden.Models.Boat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBoatRepository extends JpaRepository<Integer, Boat> {
}
