package es.rodrigo.eviden.Services;

import es.rodrigo.eviden.Interfaces.IShipownerInterface;
import es.rodrigo.eviden.Models.Shipowner;
import es.rodrigo.eviden.Models.ShipownerForm;
import es.rodrigo.eviden.Repositories.IShipownerRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IShipownerService implements IShipownerInterface {

    @Autowired
    IShipownerRepository iShipownerRepository;

    @Override
    public ResponseEntity<List<Shipowner>> getAll() {
        List<Shipowner> listShipOwner = iShipownerRepository.findAll();

        if (listShipOwner.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(listShipOwner);
    }
    @Override
    public ResponseEntity<Shipowner> getByGetReferenceId(int id) {
        Shipowner shipowner = iShipownerRepository.getReferenceById(id);
        if (shipowner==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(shipowner);
    }

    @Override
    public ResponseEntity<Shipowner> getByUsername(String username) {
        Shipowner shipowner =  iShipownerRepository.findByUsername(username);
        if (shipowner==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(shipowner);
    }

    @Override
    public ResponseEntity<Shipowner> getByDni(String Dni) {
        Shipowner shipowner = iShipownerRepository.findByDni(Dni);
        if (shipowner!=null){
            return ResponseEntity.ok(shipowner);
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<?> deleteShipowner(int id) {
        iShipownerRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Shipowner> createShipowner(ShipownerForm shipownerForm) {

        Shipowner shipowner = new Shipowner();
        shipowner.setUsername(shipownerForm.getUsername());
        shipowner.setName(shipownerForm.getName());
        shipowner.setSurname(shipownerForm.getName());
        shipowner.setCountry(shipownerForm.getCountry());
        shipowner.setDni(shipownerForm.getDni());
        shipowner.setPhone(shipownerForm.getPhone());

        iShipownerRepository.save(shipowner);

        return ResponseEntity.ok().build();
    }
}
