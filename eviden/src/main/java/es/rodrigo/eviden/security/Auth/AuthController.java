package es.rodrigo.eviden.security.Auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/auth")
@Transactional
public class AuthController {
    @Autowired
    IUser IUser;
    @Autowired
    IRole iRole;
    private AuthenticationManager authenticationManager;
    @GetMapping("/login")
    public String login(){
        return "/ViewAuth/login";
    }

    @GetMapping("/registration")
    public String registration(Model model){
        model.addAttribute("modelregister", new Registration());
        return "/ViewAuth/registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("modelregister") Registration itemRegistro, BindingResult result,Model model){
        if (result.hasErrors()){
            return "/ViewAuth/registration";
        }

        User userBuscado= IUser.obtenerPorUsuario(itemRegistro.getEmail());
        if (userBuscado!=null){
            model.addAttribute("error","El usuario ya existe.");
            return "/ViewAuth/registration";
        }

        Role roleEncontrado = iRole.obtenerRol("ROLE_USER");
        List<Role> roles = new ArrayList<>();
        roles.add(roleEncontrado);

        User usuarioNuevo = new User();
        usuarioNuevo.setUsername(itemRegistro.getNombre());
        usuarioNuevo.setEmail(itemRegistro.getEmail());
        usuarioNuevo.setUsername(itemRegistro.getEmail());
        usuarioNuevo.setPassword(new BCryptPasswordEncoder().encode(itemRegistro.getPassword()));

        usuarioNuevo.setRoles(roles);
        IUser.guardarUsuario(usuarioNuevo);
        return "/ViewAuth/login";
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping ("/usuarios")
    public String usuarios(Model model){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        model.addAttribute("username", authentication.getName());

        List<User> usuariosFiltrado = IUser.obtenerUsuarios().stream()
                .filter(usuario -> !usuario.getUsername().equals(username))
                .collect(Collectors.toList());

        model.addAttribute("usuarios",usuariosFiltrado);

        return "/ViewAuth/usuarios";
    }


    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/eliminar/{id}")
    public String eliminar(Model model, @PathVariable int id){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Optional<User> usuarioBuscado =IUser.encontrarPorId(id);
        if (usuarioBuscado.isPresent() && username.equals(usuarioBuscado.get().getUsername())){
            return "redirect:/auth/usuarios";
        }

        IUser.eliminarUserPorId(id);

        return "redirect:/auth/usuarios";
    }
}
