package es.rodrigo.eviden.Repositories;

import es.rodrigo.eviden.Models.Partner;
import es.rodrigo.eviden.Models.Shipowner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IShipownerRepository extends JpaRepository<Shipowner,Integer> {
    Shipowner findByUsername(String username);
    Shipowner findByDni(String Dni);
}
