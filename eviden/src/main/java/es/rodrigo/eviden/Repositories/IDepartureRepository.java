package es.rodrigo.eviden.Repositories;

import es.rodrigo.eviden.Models.Departure;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDepartureRepository extends JpaRepository<Integer, Departure> {
    Departure findById(int id);
}
