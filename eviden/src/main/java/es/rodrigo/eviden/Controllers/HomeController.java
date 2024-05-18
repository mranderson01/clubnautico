package es.rodrigo.eviden.Controllers;


import es.rodrigo.eviden.Interfaces.IHomeInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @Autowired
    IHomeInterface iHomeImpl;
    @GetMapping("/")
    @PreAuthorize("hasRole('OWNER') || hasRole('ADMIN') || hasRole('USER')")
    public ResponseEntity<String> index(Authentication authentication, Model model){

        String informacion = iHomeImpl.obtenerMensaje();
        return new ResponseEntity<>(informacion, HttpStatus.OK);
    }
}
