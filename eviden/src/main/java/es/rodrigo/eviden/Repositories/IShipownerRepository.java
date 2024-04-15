package es.rodrigo.eviden.Repositories;

import es.rodrigo.eviden.Models.Boat;
import es.rodrigo.eviden.Models.Shipowner;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface IShipownerRepository extends JpaRepository<Shipowner,Integer> {
    Shipowner findByUsername(String username);
    Shipowner findByDni(String Dni);
    List<Boat> findBoatByUsername(String Username);
}
