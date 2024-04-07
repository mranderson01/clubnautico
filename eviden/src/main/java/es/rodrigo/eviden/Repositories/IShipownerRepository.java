package es.rodrigo.eviden.Repositories;

import es.rodrigo.eviden.Models.Partner;
import es.rodrigo.eviden.Models.Shipowner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IShipownerRepository extends JpaRepository<Integer, IShipownerRepository> {
    Shipowner findByUsername(String username);
    Shipowner findByDni(String Dni);
}
