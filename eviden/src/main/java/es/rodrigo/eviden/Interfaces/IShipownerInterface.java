package es.rodrigo.eviden.Interfaces;

import es.rodrigo.eviden.Models.Shipowner;
import es.rodrigo.eviden.Models.ShipownerForm;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IShipownerInterface {
    ResponseEntity<List<Shipowner>> getAll();
    ResponseEntity<Shipowner> getByGetReferenceId(int idReferenced);
    ResponseEntity<Shipowner> getByUsername(String username);
    ResponseEntity<?> deleteShipowner(int id);
    ResponseEntity<?> createShipowner(ShipownerForm shipowner);
}
