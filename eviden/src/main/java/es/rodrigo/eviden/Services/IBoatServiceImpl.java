package es.rodrigo.eviden.Services;

import es.rodrigo.eviden.Interfaces.IBoatInterface;
import es.rodrigo.eviden.Models.Boat;
import es.rodrigo.eviden.Models.CreationBoatForm;
import es.rodrigo.eviden.Models.Shipowner;
import es.rodrigo.eviden.Repositories.IBoatRepository;
import es.rodrigo.eviden.Repositories.IShipownerRepository;
import es.rodrigo.eviden.Repositories.IUserRepository;
import es.rodrigo.eviden.security.ModelSecurity.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Transactional
public class IBoatServiceImpl implements IBoatInterface {

    @Autowired
    IBoatRepository iBoatRepository;

    @Autowired
    IShipownerRepository iShipownerRepository;

    @Autowired
    IUserRepository iUserRepository;

    @Override
    public ResponseEntity<List<Boat>> getAll() {
        UserDetails userSeek = obtenerUserDetails();
        String username = userSeek.getUsername();

        Optional<User> user = iUserRepository.findByUsername(username);
        if (user.isEmpty()){
            return ResponseEntity.notFound().build();
        }


        List<Boat> listaBoat = iBoatRepository.findAllBoatByDni(user.get().getDni());
        //List<Boat> listaBoat = iBoatRepository.findAllBoatByDni(user.get().getDni());

        if (listaBoat.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(listaBoat);
        }
    }

    @Override
    public ResponseEntity<?> findById(int id) {

        Optional<Boat> boat  = iBoatRepository.findById(id);

        if (boat.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(boat);
    }

    @Override
    public ResponseEntity<?> findByName(String name) {

        Optional<Boat> oneBoat = iBoatRepository.findByName(name);

        if (oneBoat.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public ResponseEntity<?> findByNameenrollment(String Nameenrollment) {

        Optional<Boat> boat = iBoatRepository.findByNameenrollment(Nameenrollment);

        if (boat.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(boat);
    }

    @Override
    public ResponseEntity<?> findbyNumberberth(int Numberberth) {
        Optional<Boat> boat = iBoatRepository.findByNumberberth(Numberberth);

        if (boat.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(boat.get());
    }

    @Override
    public ResponseEntity<?> createBoat(CreationBoatForm creationBoatForm) {

        Boat boat  = new Boat();
        boat.setNameenrollment(creationBoatForm.getNameenrollment());
        boat.setName(creationBoatForm.getName());
        boat.setNumberberth(creationBoatForm.getNumberberth());
        boat.setFee(creationBoatForm.getFee());

        //añado un usuario
        int idShipowner = creationBoatForm.getIdShipowner();
        Shipowner shipowner = iShipownerRepository.getReferenceById(idShipowner);
        Optional<Shipowner> shipownerOpt = Optional.ofNullable(iShipownerRepository.findByDni(shipowner.getUser().getDni()));

        HashSet<Shipowner> listShipowner = new HashSet<>();
        listShipowner.add(shipowner);
        boat.setShipowners(listShipowner);

        //guardar
        iBoatRepository.save(boat);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


    @Override
    public ResponseEntity<?> deleteBoatByName(String Name) {

        iBoatRepository.deleteByName(Name);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Override
    public ResponseEntity<?> deleteById(int id) {
        iBoatRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Override
    public ResponseEntity<?> findBoatsByUsername() {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = ((UserDetails)principal);

        Optional<Boat> listBoat = iBoatRepository.findBoatByIdAndUsername(userDetails.getUsername());

        if (listBoat.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(listBoat);
    }

    @Override
    public ResponseEntity<?> findBoatsByUsername(String username) {

        Optional<Boat> listBoat = iBoatRepository.findBoatByIdAndUsername(username);

        if (listBoat.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(listBoat);
    }

    @Override
    public ResponseEntity<?> updateBoat(int id,CreationBoatForm boatInserted,Boat boat) {

        boat.setName(boatInserted.getName());
        boat.setNameenrollment(boatInserted.getNameenrollment());
        boat.setNumberberth(boatInserted.getNumberberth());
        boat.setFee(boatInserted.getFee());

        //añadir los propietarios de ese barco.
        Set<Shipowner> listShipowner = new HashSet<>();
        int idShipowner = boatInserted.getIdShipowner();
        Shipowner shipowner = iShipownerRepository.getReferenceById(idShipowner);

        boat.getShipowners().add(shipowner);

        iBoatRepository.save(boat);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    public UserDetails obtenerUserDetails(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ((UserDetails)principal);
    }
}