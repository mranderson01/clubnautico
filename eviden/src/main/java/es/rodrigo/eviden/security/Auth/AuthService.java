package es.rodrigo.eviden.security.Auth;


import es.rodrigo.eviden.Repositories.IRoleRepository;
import es.rodrigo.eviden.Repositories.IUserRepository;
import es.rodrigo.eviden.security.Jwt.JwtService;
import es.rodrigo.eviden.security.ModelSecurity.Role;
import es.rodrigo.eviden.security.ModelSecurity.User;
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
    private IUserRepository iUserRepository;

    @Autowired
    IRoleRepository iRoleRepository;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private  AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        Optional<User> user =  iUserRepository.findByUsername(request.getUsername());

        if (user.isEmpty()){
            return AuthResponse
                    .builder()
                    .token("Hubo un error en el usuario o contraseña")
                    .build();
        }

        String token = jwtService.getToken((UserDetails) user.get());
        return AuthResponse
                .builder()
                .token(token)
                .build();
    }

    public AuthResponse register(RegisterRequest request) {


        List<Role> roles = new ArrayList<>();

        Optional<Role> basicRole = iRoleRepository.findRoleByName("ROLE_USER");

        basicRole.ifPresent(roles::add);

        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode( request.getPassword()))
                .firstname(request.getFirstname())
                .lastname(request.lastname)
                .country(request.getCountry())
                .roles(roles)
                .build();

        iUserRepository.save(user);

        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .build();
    }
}
