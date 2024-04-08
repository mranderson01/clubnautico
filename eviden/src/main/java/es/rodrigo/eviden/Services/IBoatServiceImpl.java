package es.rodrigo.eviden.Services;

import es.rodrigo.eviden.Interfaces.IBoatInterface;
import es.rodrigo.eviden.Models.Boat;
import es.rodrigo.eviden.Repositories.IBoatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IBoatServiceImpl implements IBoatInterface {

    @Autowired
    IBoatRepository iBoatRepository;

    @Override
    public ResponseEntity<List<Boat>> getAll() {
        List<Boat> listaBoat = iBoatRepository.findAll();
        if (listaBoat.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(listaBoat);
        }
    }

    @Override
    public ResponseEntity<?> findByName(String name) {
        Optional<Boat> oneBoat = iBoatRepository.findByName(name);

        if (oneBoat.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se pudo encontrar el barco");
        }

        if (oneBoat.isPresent()){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(oneBoat.get());
        }

        // En caso de que ocurra algo inesperado, como una excepción, puedes devolver un estado 500 con un mensaje genérico
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error inesperado");
    }

    @Override
    public Optional<Boat> findByNameenrollment(String Nameenrollment) {
        return iBoatRepository.findByNameenrollment(Nameenrollment);
    }

    @Override
    public Boat findbyNumberberth(int Numberberth) {
        return iBoatRepository.findByNumberberth(Numberberth);
    }

    @Override
    public void createBoat(Boat boat) {
        iBoatRepository.save(boat);
    }

    @Override
    public void deleteBoat(int id) {
        iBoatRepository.deleteById(id);
    }
}
