package es.rodrigo.eviden.Interfaces;

import es.rodrigo.eviden.Models.Boat;
import es.rodrigo.eviden.Models.CreationBoatForm;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface IBoatInterface {
    ResponseEntity<List<Boat>> getAll();
    ResponseEntity<?> findById(int id);
    ResponseEntity<?> findByName(String Name);
    ResponseEntity<?> findByNameenrollment(String Nameenrollment);
    ResponseEntity<?> findbyNumberberth(int Numberberth);

    ResponseEntity<?> createBoat(CreationBoatForm boat);
    ResponseEntity<?> deleteBoatByName(String Name);
    ResponseEntity<?> deleteById(int id);

    ResponseEntity<?>  findBoatsByUsername();
    ResponseEntity<?>  findBoatsByUsername(String username);

    ResponseEntity<?> updateBoat(int id,CreationBoatForm boatInserted,Boat boat);
}
