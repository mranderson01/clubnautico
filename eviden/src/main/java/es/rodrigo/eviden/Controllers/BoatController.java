package es.rodrigo.eviden.Controllers;

import es.rodrigo.eviden.Interfaces.IBoatInterface;
import es.rodrigo.eviden.Models.Boat;
import es.rodrigo.eviden.Models.CreationBoatForm;
import es.rodrigo.eviden.Services.IBoatServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/boats")
public class BoatController {

    @Autowired
    IBoatServiceImpl iBoatInterface;

    @GetMapping("/")
    @PreAuthorize("hasRole('OWNER') || hasRole('ADMIN')")
    ResponseEntity<List<Boat>> index() {

        ResponseEntity<List<Boat>> responseEntity = iBoatInterface.getAll();

        if (responseEntity.getStatusCode().is2xxSuccessful()) {

            List<Boat> boatList = responseEntity.getBody();
            assert boatList != null;
            if (!boatList.isEmpty()) {
                return ResponseEntity.ok(responseEntity.getBody());
            } else {
                return ResponseEntity.notFound().build();
            }

        } else {
            return ResponseEntity.status(responseEntity.getStatusCode()).build();
        }
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') || hasRole('WORKER') || hasRole('OWNER')")
    ResponseEntity<?> getOneboat(@PathVariable int id){
        ResponseEntity<?> responseEntity = iBoatInterface.findbyNumberberth(id);

        if (responseEntity.getStatusCode().is2xxSuccessful()){
            return  ResponseEntity.status(200).body(responseEntity.getBody());
        }
        if (responseEntity.getStatusCode().is4xxClientError()){
            return ResponseEntity.status(404).body("No se pudo encontrar el barco con ese numero de amarre.");
        }
        return  ResponseEntity.status(500).body("Hubo un error en el servidor. Contacte con el administrador");
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN') || hasRole('WORKER') || hasRole('OWNER')")
    ResponseEntity<?> create( @Valid @RequestBody CreationBoatForm creationBoatForm,
                              BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return  ResponseEntity.status(400).body("El Barco que se inserto esta incorrecto. Vuelva a intentarlo.");
        }

        ResponseEntity<?> responseEntity = iBoatInterface.createBoat(creationBoatForm);

        if (responseEntity.getStatusCode().is2xxSuccessful()){
            return  ResponseEntity.status(200).body(responseEntity.getBody());
        }

        if (responseEntity.getStatusCode().is4xxClientError()){
            return ResponseEntity.status(404).body("No se pudo encontrar el barco con ese numero de amarre.");
        }

        return  ResponseEntity.status(500).body("Hubo un error en el servidor. Contacte con el administrador");
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') || hasRole('WORKER') || hasRole('OWNER')")
    ResponseEntity<?> updateBoat(@PathVariable int id,
                                 @Valid @RequestBody  CreationBoatForm boat,
                                 BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return  ResponseEntity.status(400).body("El Barco que se inserto esta incorrecto. Vuelva a intentarlo.");
        }

        ResponseEntity<?> responseEntity = iBoatInterface.findbyNumberberth(id);
        if (responseEntity.getStatusCode().is4xxClientError()){
            return  ResponseEntity.status(404).body("No se encuentra el barco segun el numero de amarre.");
        }

        if (responseEntity.getStatusCode().is2xxSuccessful()){
            iBoatInterface.updateBoat(id,boat, (Boat) responseEntity.getBody());
            return  ResponseEntity.status(200).body("Se actualizo correctamente el barco");
        }

        return  ResponseEntity.status(500).body("Hubo un error en el servidor. Contacte con el administrador");
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ADMIN') || hasRole('WORKER') || hasRole('OWNER')")
    ResponseEntity<?> deleteBoat(@PathVariable int id){

        ResponseEntity<?> responseEntity = iBoatInterface.deleteById(id);

        if (responseEntity.getStatusCode().is2xxSuccessful()){
            return  ResponseEntity.status(200).body("Se elimino correctamente el barco");
        }

        if (responseEntity.getStatusCode().is4xxClientError()){
            return  ResponseEntity.status(404).body("Hubo un error en el id.");
        }

        return  ResponseEntity.status(500).body("Hubo un error en el servidor. Contacte con el administrador");
    }

    @GetMapping("/boatsByUsername")
    @PreAuthorize("hasRole('ADMIN') || hasRole('WORKER')")
    ResponseEntity<?> getBoatsByUsername(){

        ResponseEntity<?> responseEntity = iBoatInterface.findBoatsByUsername();
        if (responseEntity.getStatusCode().is2xxSuccessful()){
            return ResponseEntity.status(200).body(responseEntity);
        }

        if (responseEntity.getStatusCode().is4xxClientError()){
            return ResponseEntity.status(404).body(responseEntity);
        }

        return ResponseEntity.status(500).body("hubo un error en el servidor. Contacta con el administrador.");
    }


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{username}")
    ResponseEntity<?> boatsToAdminFromUser(@PathVariable String username){

        ResponseEntity<?> responseEntity = iBoatInterface.findBoatsByUsername(username);

        if (responseEntity.getStatusCode().is2xxSuccessful()){
            return ResponseEntity.status(200).body(responseEntity);
        }

        if (responseEntity.getStatusCode().is4xxClientError()){
            return ResponseEntity.status(404).body(responseEntity);
        }

        return ResponseEntity.status(500).body("hubo un error en el servidor. Contacta con el administrador.");
    }
}
