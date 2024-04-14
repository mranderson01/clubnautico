package es.rodrigo.eviden.Services;

import es.rodrigo.eviden.Interfaces.IBoatInterface;
import es.rodrigo.eviden.Models.Boat;
import es.rodrigo.eviden.Models.CreationBoatForm;
import es.rodrigo.eviden.Models.Shipowner;
import es.rodrigo.eviden.Repositories.IBoatRepository;
import es.rodrigo.eviden.Repositories.IShipownerRepository;
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

        //buscar el objeto shipowner segun el username que es un correo para ver si existen en la base de datos:

        //añado estos usuarios
        Set<Shipowner> owners = new HashSet<>();

        String [] userNames = creationBoatForm.getUsernames();
        for (String userName : userNames) {
            Optional<Shipowner> shipownerOpt = Optional.ofNullable(iShipownerRepository.findByUsername(userName));
            if (shipownerOpt.isEmpty()){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }

            //guardar en tabla intermedia
            /*Shipownerboat shipownerboat = new Shipownerboat();
            shipownerboat.setBoat_id(boat.getId());
            shipownerboat.setShipowner_id(shipownerOpt.get().getId());*/

            //añadir el propietario en la lista de propietarios
            shipownerOpt.ifPresent(owners::add);
        }
        boat.setShipowners(owners);

        //guardar
        iBoatRepository.saveAndFlush(boat);
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
    public ResponseEntity<?> findBoatByUsername() {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = ((UserDetails)principal);

        List<Boat> listBoat = iShipownerRepository.findBoatByUsername(userDetails.getUsername());

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
        List<String> usernamesList = Arrays.asList(boatInserted.getUsernames());

        if (!usernamesList.isEmpty()){
            usernamesList.forEach(username -> {
                listShipowner.add(iShipownerRepository.findByUsername(username));
            });
        }

        boat.setShipowners(listShipowner);

        iBoatRepository.save(boat);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}