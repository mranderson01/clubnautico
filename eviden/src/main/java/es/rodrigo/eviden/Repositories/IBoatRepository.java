package es.rodrigo.eviden.Repositories;

import es.rodrigo.eviden.Models.Boat;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Transactional
public interface IBoatRepository extends JpaRepository<Boat,Integer> {
    Optional<Boat> findByNumberberth(int Numberberth);
    Optional<Boat> findByName(String Name);
    Optional<Boat> findByNameenrollment(String Nameenrollment);
    void deleteByName(String Name);
    void deleteById(int id);

}
