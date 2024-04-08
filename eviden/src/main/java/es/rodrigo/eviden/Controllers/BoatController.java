package es.rodrigo.eviden.Controllers;

import es.rodrigo.eviden.Interfaces.IBoatInterface;
import es.rodrigo.eviden.Models.Boat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/boats")
public class BoatController {
    @Autowired
    IBoatInterface iBoatInterface;

    @GetMapping("/")
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

    @GetMapping("{id}")
    ResponseEntity<Boat> getOneboat(@PathVariable int id){
        ResponseEntity<Boat> getBoat = iBoatInterface.findbyNumberberth(id);
    }
}
