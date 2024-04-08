package es.rodrigo.eviden.Repositories;

import es.rodrigo.eviden.Models.Departure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IDepartureRepository extends JpaRepository<Departure,Integer> {
    @Override
    Optional<Departure> findById(Integer integer);
}
