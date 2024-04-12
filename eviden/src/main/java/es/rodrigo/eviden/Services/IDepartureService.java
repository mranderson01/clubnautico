package es.rodrigo.eviden.Services;

import es.rodrigo.eviden.Interfaces.IDepartureInterface;
import es.rodrigo.eviden.Models.Departure;
import es.rodrigo.eviden.Models.DepartureForm;
import es.rodrigo.eviden.Repositories.IDepartureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IDepartureService implements IDepartureInterface {

    @Autowired
    IDepartureRepository iDepartureRepository;
    @Override
    public ResponseEntity<?> getAll() {

        List<Departure> departures = iDepartureRepository.findAll();
        if (departures.isEmpty()){
             return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok().body(departures);
    }

    @Override
    public ResponseEntity<?> getById(int id) {
        Optional<Departure> departure = iDepartureRepository.findById(id);
        if (departure.isEmpty()){
            return ResponseEntity.status(404).body("No se pudo encontrar la salida con id: "+id);
        }
        return ResponseEntity.status(200).body(departure);
    }

    @Override
    public ResponseEntity<?> createDeparture(DepartureForm departureForm) {

        Departure departureNuevo = new Departure();
        departureNuevo.setTime(departureForm.getTime());
        departureNuevo.setDestination(departureForm.getDestination());
        departureNuevo.setTime(departureForm.getTime());

        iDepartureRepository.save(departureNuevo);
        return ResponseEntity.status(200).body("Se creo la salida.");
    }

    @Override
    public ResponseEntity<?> deleteDeparture(int id) {
        iDepartureRepository.deleteById(id);
        return ResponseEntity.status(200).body("se eliminio la salida.");
    }

    @Override
    public ResponseEntity<?> updateDeparture(DepartureForm departureForm,int id) {

        Optional<Departure> departure = iDepartureRepository.findById(id);
        if (departure.isPresent()){
            departure.get().setDate(departureForm.getDate());
            departure.get().setTime(departureForm.getTime());
            departure.get().setDestination(departureForm.getDestination());

            iDepartureRepository.save(departure.get());
            return  ResponseEntity.ok().build();
        }else {
            return ResponseEntity.badRequest().build();
        }
    }
}
