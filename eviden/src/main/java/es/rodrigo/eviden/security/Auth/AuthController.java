package es.rodrigo.seguridad.security.Auth;

import es.rodrigo.eviden.Models.Response.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping(value = "login")
    public ResponseEntity<?> login(@RequestBody es.rodrigo.seguridad.security.Auth.LoginRequest request)
    {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping(value = "register")
    public ResponseEntity<?> register(@RequestBody es.rodrigo.seguridad.security.Auth.RegisterRequest request)
    {

        return ResponseEntity.ok(authService.register(request));
    }
}
