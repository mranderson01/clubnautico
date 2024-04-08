package es.rodrigo.eviden.Interfaces;

import es.rodrigo.eviden.Models.Boat;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface IBoatInterface {
    ResponseEntity<List<Boat>> getAll();
    Optional<Boat> findByName(String Name);
    Optional<Boat> findByNameenrollment(String Nameenrollment);
    Boat findbyNumberberth(int Numberberth);

    void createBoat(Boat boat);
    void deleteBoat(int id);
}
