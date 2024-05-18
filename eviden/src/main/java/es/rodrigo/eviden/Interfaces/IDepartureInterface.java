package es.rodrigo.eviden.Interfaces;

import es.rodrigo.eviden.Models.Departure;
import es.rodrigo.eviden.Models.DepartureForm;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface IDepartureInterface {
    ResponseEntity<?> getAll();
    ResponseEntity<?> getById(int id);
    ResponseEntity<?> createDeparture(DepartureForm departureForm);
    ResponseEntity<?> deleteDeparture(int id);
    ResponseEntity<?> updateDeparture(DepartureForm departureForm,int id);


    ResponseEntity<?> getDeparturesByUsername(String username);
}
