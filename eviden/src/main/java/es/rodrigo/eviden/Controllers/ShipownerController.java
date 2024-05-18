package es.rodrigo.eviden.Controllers;

import es.rodrigo.eviden.Interfaces.IShipownerInterface;
import es.rodrigo.eviden.Models.Shipowner;
import es.rodrigo.eviden.Models.ShipownerForm;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shipowner")
public class ShipownerController {
    @Autowired
    IShipownerInterface iShipownerInterface;

    //obtener todos los propietarios
    @GetMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<?> getall(){
         ResponseEntity<List<Shipowner>> responseEntity = iShipownerInterface.getAll();

         if (responseEntity.getStatusCode().is2xxSuccessful()){
             return ResponseEntity.status(200).body(responseEntity.getBody());
         }

        if (responseEntity.getStatusCode().is4xxClientError()){
            return ResponseEntity.status(404).body("No se pudo encontrar la lista de propietarios");
        }

        return ResponseEntity.status(500).body("error en el servidor.");
    }

    @GetMapping("/{dni}")
    @PreAuthorize("hasRole('OWNER') || hasRole('ADMIN')")
    ResponseEntity<?> getOne(@PathVariable String dni){
        ResponseEntity<Shipowner> responseEntity = iShipownerInterface.getByDni(dni);

        if (responseEntity.getStatusCode().is2xxSuccessful()){
            return ResponseEntity.status(200).body(responseEntity.getBody());
        }

        if (responseEntity.getStatusCode().is4xxClientError()){
            return ResponseEntity.status(404).body("No se pudo encontrar el propietario");
        }

        return ResponseEntity.status(500).body("error en el servidor.");
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<?> create(@Valid @RequestBody ShipownerForm shipownerForm,
                             BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return  ResponseEntity.status(400).body("El Propietario que se inserto esta incorrecto. Vuelva a intentarlo.");
        }
         ResponseEntity<?> responseEntity =  iShipownerInterface.createShipowner(shipownerForm);
        if (responseEntity.getStatusCode().is2xxSuccessful()){
            return  ResponseEntity.status(200).body("Se creo corramente un propietario.");
        }
        return  ResponseEntity.status(500).body("Problema interno en el servidor. Contacto con el administrador.");
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<?> updateShipowner(@Valid @RequestBody ShipownerForm shipownerForm,
                                      BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return  ResponseEntity.status(400).body("El Propietario que se inserto esta incorrecto. Vuelva a intentarlo.");
        }

        ResponseEntity<?> responseEntity =  iShipownerInterface.createShipowner(shipownerForm);

        if (responseEntity.getStatusCode().is2xxSuccessful()){
            return  ResponseEntity.status(200).body(responseEntity.getBody());
        }

        return  ResponseEntity.status(500).body("Problema interno en el servidor. Contacto con el administrador.");
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<?> deleteShipowner(@PathVariable int id){

        if (id<0){
            return ResponseEntity.status(404).body("Se tiene que introducir una id correcta.");
        }

        ResponseEntity<?> responseEntity = iShipownerInterface.deleteShipowner(id);

        if (responseEntity.getStatusCode().is2xxSuccessful()){
            return  ResponseEntity.status(200).body("Se elimino correctamente el propietario.");
        }

        return  ResponseEntity.status(500).body("Error en el servidor. Contacto con el administrador del sistema.");
    }
}
