package es.rodrigo.eviden.Services;

import es.rodrigo.eviden.Interfaces.IShipownerInterface;
import es.rodrigo.eviden.Models.Shipowner;
import es.rodrigo.eviden.Models.ShipownerForm;
import es.rodrigo.eviden.Repositories.IShipownerRepository;
import es.rodrigo.eviden.Repositories.IUserRepository;
import es.rodrigo.eviden.security.ModelSecurity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IShipownerService implements IShipownerInterface {

    @Autowired
    IShipownerRepository iShipownerRepository;

    @Autowired
    IUserRepository iUserRepository;

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
    public ResponseEntity<Shipowner> getByDni(String Dni) {

        UserDetails userDetails = obtenerUserDetails();
        String username = userDetails.getUsername();

        Optional<User> usuario = iUserRepository.findByUsername(username);

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

        Optional<User> user = iUserRepository.findById(shipownerForm.getIdUser());
        if (user.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        Shipowner shipowner = new Shipowner();
        shipowner.setCountry(shipownerForm.getCountry());
        shipowner.setUser(user.get());
        iShipownerRepository.save(shipowner);

        return ResponseEntity.ok().build();
    }

    public UserDetails obtenerUserDetails(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ((UserDetails)principal);
    }
}
