package es.rodrigo.eviden.Controllers;

import es.rodrigo.eviden.security.ModelSecurity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {



    @GetMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> index(){
        System.out.println("hola");
        return ResponseEntity.status(200).body("Recurso encontrado: "); // Estado 200 OK
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') || hasRole('OWNER') || hasRole('USER')")
    public ResponseEntity<String> obtenerPorId(
                                               @PathVariable int id){
        return ResponseEntity.status(200).body("Se esta buscando el usuario: "+id); // Estado 200 OK
    }



    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> actualizarUser(
                                                 @PathVariable int id,@RequestBody User ProjectoEditado){
        return ResponseEntity.status(200).body("Se ha editado el projecto con id: "+id + "Estos son los datos introducidos: "+ProjectoEditado); // Estado 200 OK
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> eliminarPorId(
                                                 @PathVariable int id){
        return ResponseEntity.status(200).body("Se borrar este recurso con id: "+id); // Estado 200 OK
    }
}
