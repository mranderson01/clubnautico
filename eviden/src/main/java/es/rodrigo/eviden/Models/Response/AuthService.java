package es.rodrigo.eviden.Models.Response;


import es.rodrigo.eviden.Exception.UserNotFoundException;
import es.rodrigo.eviden.Models.Response.AuthResponse;
import es.rodrigo.eviden.Models.Role;
import es.rodrigo.eviden.Models.User;
import es.rodrigo.eviden.Repositories.IRoleRepository;
import es.rodrigo.eviden.Repositories.IUserRepository;
import es.rodrigo.eviden.security.Auth.LoginRequest;
import es.rodrigo.eviden.security.Auth.RegisterRequest;
import es.rodrigo.eviden.security.Jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    @Autowired
    IUserRepository iUserRepository;

    @Autowired
    IRoleRepository iRoleRepository;

    @Autowired
    JwtService jwtService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    public String login(LoginRequest request){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword()));
        UserDetails user= iUserRepository.findByUsername(request.getUsername()).get();
        String token = jwtService.getToken(user);

        return token;
    }
    public String register(RegisterRequest registerRequest){
        List<Role> roles = new ArrayList<>();

        Optional<Role> basicRole = iRoleRepository.findByNombre("ROLE_USER");
        if (basicRole.isPresent()) {
            roles.add(basicRole.get());
        }

        User userRegisterRequest =
                User
                        .builder()
                        .firstname(registerRequest.getFirstname())
                        .lastname(registerRequest.getLastname())
                        .username(registerRequest.getUsername())
                        .password(passwordEncoder.encode(registerRequest.getPassword()))
                        .roles(roles)
                        .build();


        iUserRepository.saveAndFlush(userRegisterRequest);

        return jwtService.getToken(userRegisterRequest);
    }


    public String delete(Integer id, UserDetails userDetails) {
        String username = userDetails.getUsername();

        UserDetails usuarioActual = iUserRepository.findByUsername(username).get();
        User usuarioABorrar = iUserRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));

        if (usuarioActual.getUsername().equals(usuarioABorrar.getUsername())) {
            return "No puedes borrarte a ti mismo.";
        }

        iUserRepository.delete(usuarioABorrar);

        return "Usuario borrado";
    }

    public List<User> usuarios (){
        return iUserRepository.findAll();
    }
}