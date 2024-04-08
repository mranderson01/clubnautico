package es.rodrigo.eviden.Repositories;

import es.rodrigo.eviden.Models.Boat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBoatRepository extends JpaRepository<Boat,Integer> {
    Boat findByNumberberth(int Numberberth);
}
