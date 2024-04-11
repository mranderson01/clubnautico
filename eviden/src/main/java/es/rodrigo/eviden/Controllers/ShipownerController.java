package es.rodrigo.eviden.Controllers;

import es.rodrigo.eviden.Interfaces.IShipownerInterface;
import es.rodrigo.eviden.Models.Shipowner;
import es.rodrigo.eviden.Models.ShipownerForm;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    ResponseEntity<?> getall(){
         ResponseEntity<List<Shipowner>> responseEntity = iShipownerInterface.getAll();

         if (responseEntity.getStatusCode().is2xxSuccessful()){
             return ResponseEntity.ok(responseEntity.getBody());
         }

        if (responseEntity.getStatusCode().is4xxClientError()){
            return ResponseEntity.status(404).body("No se pudo encontrar la lista de propietarios");
        }

        return ResponseEntity.status(500).body("error en el servidor.");
    }

    @GetMapping("/{id}")
    ResponseEntity<?> getOne(@PathVariable int id){
        ResponseEntity<Shipowner> responseEntity = iShipownerInterface.getByGetReferenceId(id);

        if (responseEntity.getStatusCode().is2xxSuccessful()){
            return ResponseEntity.ok(responseEntity.getBody());
        }

        if (responseEntity.getStatusCode().is4xxClientError()){
            return ResponseEntity.status(404).body("No se pudo encontrar la lista de propietarios");
        }

        return ResponseEntity.status(500).body("error en el servidor.");
    }

    @PostMapping("/create")
    ResponseEntity<?> create(@Valid @RequestBody ShipownerForm shipownerForm,
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

    @PutMapping("/{id}")
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
}
