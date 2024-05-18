package es.rodrigo.eviden.Controllers;

import es.rodrigo.eviden.Interfaces.IBoatInterface;
import es.rodrigo.eviden.Interfaces.IDepartureInterface;
import es.rodrigo.eviden.Models.Departure;
import es.rodrigo.eviden.Models.DepartureForm;
import es.rodrigo.eviden.Models.ShipownerForm;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departure")
public class DepartureController {

    @Autowired
    private IDepartureInterface iDepartureInterface;

    @GetMapping("/")
    @PreAuthorize("hasRole('OWNER')")
    ResponseEntity<?> getAll(){
        ResponseEntity<?> responseEntity = iDepartureInterface.getAll();

        if (responseEntity.getStatusCode().is2xxSuccessful()){
           return  ResponseEntity.status(200).body(responseEntity.getBody());
        }

        return  ResponseEntity.status(500).body("Hubo un error interno del servidor, contacto con el administrador.");
    }

    @GetMapping("/{username}")
    @PreAuthorize("hasRole('OWNER')")
    ResponseEntity<?> getAll(@PathVariable String username){
        ResponseEntity<?> responseEntity = iDepartureInterface.getDeparturesByUsername(username);

        if (responseEntity.getStatusCode().is2xxSuccessful()){
            return  ResponseEntity.status(200).body(responseEntity.getBody());
        }

        return  ResponseEntity.status(500).body("Hubo un error interno del servidor, contacto con el administrador.");
    }



    @GetMapping("/{id}")
    @PreAuthorize("hasRole('OWNER') || hasRole('ADMIN')")
    ResponseEntity<?> getOne(@PathVariable int id){
        ResponseEntity<?> responseEntity = iDepartureInterface.getById(id);

        if (responseEntity.getStatusCode().is4xxClientError()){
            return  ResponseEntity.status(400).body(responseEntity.getBody());
        }

        return  ResponseEntity.status(200).body(responseEntity.getBody());
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<?> updateDeparture(@PathVariable int id,
                                      @Valid @RequestBody DepartureForm departureForm,
                                      BindingResult bindingResult){

        if (bindingResult.hasErrors()){
            return ResponseEntity.status(404).body("Hubo un error en el formulario de salida del barco.");
        }

        ResponseEntity<?> responseEntity = iDepartureInterface.updateDeparture(departureForm,id);

        if (responseEntity.getStatusCode().is4xxClientError()){
            return ResponseEntity.status(404).body("No se pudo encontrar la id de la salida.");
        }

        if (responseEntity.getStatusCode().is2xxSuccessful()){
            return ResponseEntity.status(200).body(responseEntity.getBody());
        }

        return ResponseEntity.status(500).body("Hubo un problema en el servidor. Contacte con el administrador.");
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<?>deleteDeparture(@PathVariable int id){

        if (id<=0){
            return ResponseEntity.status(404).body("No se puede introducir una id por debajo o igual de 0");
        }
        ResponseEntity<?> responseEntity = iDepartureInterface.deleteDeparture(id);

        if (responseEntity.getStatusCode().is2xxSuccessful()){
            return ResponseEntity.status(200).body(responseEntity.getBody());
        }

        return ResponseEntity.status(500).body("Error en el servidor. ");
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<?> postDeparture(@Valid @RequestBody DepartureForm departureForm,
                                    BindingResult bindingResult){
        ResponseEntity<?> responseEntity = iDepartureInterface.createDeparture(departureForm);

        if (responseEntity.getStatusCode().is4xxClientError()){
            return ResponseEntity.badRequest().body(responseEntity);
        }

        return ResponseEntity.ok().body(responseEntity);
    }

}
