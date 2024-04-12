package es.rodrigo.eviden.Controllers;

import es.rodrigo.eviden.Interfaces.IBoatInterface;
import es.rodrigo.eviden.Interfaces.IDepartureInterface;
import es.rodrigo.eviden.Models.Departure;
import es.rodrigo.eviden.Models.DepartureForm;
import es.rodrigo.eviden.Models.ShipownerForm;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departure")
public class DepartureController {

    @Autowired
    private IDepartureInterface iDepartureInterface;

    @GetMapping("/")
    ResponseEntity<?> getAll(){
        ResponseEntity<?> responseEntity = iDepartureInterface.getAll();

        if (responseEntity.getStatusCode().is2xxSuccessful()){
           return  ResponseEntity.status(200).body(responseEntity.getBody());
        }

        return  ResponseEntity.status(500).body("Hubo un error interno del servidor, contacto con el administrador.");
    }

    @GetMapping("/{id}")
    ResponseEntity<?> getOne(@PathVariable int id){
        ResponseEntity<?> responseEntity = iDepartureInterface.getById(id);

        if (responseEntity.getStatusCode().is4xxClientError()){
            return  ResponseEntity.status(400).body(responseEntity.getBody());
        }

        return  ResponseEntity.status(200).body(responseEntity.getBody());
    }

    @PutMapping("/{id}")
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
}
