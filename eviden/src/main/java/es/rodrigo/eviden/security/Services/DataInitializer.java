package es.rodrigo.eviden.security.Services;

import es.rodrigo.eviden.security.Models.Role;
import es.rodrigo.eviden.security.Models.User;
import es.rodrigo.eviden.security.repositories.IRoleRepository;
import es.rodrigo.eviden.security.repositories.IUserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
@Service
public class DataInitializer implements CommandLineRunner {
    @Autowired
    private IUserRepository usuarioRepository;
    @Autowired
    private IRoleRepository rolRepository;


    @Override
    @Transactional
    public void run(String... args) throws Exception {

        //toque aqui tambien

        Role rolAdmin = rolRepository.findRoleByName("ROLE_ADMIN");
        if (rolAdmin == null) {
            rolAdmin = new Role();
            rolAdmin.setName("ROLE_ADMIN");
            rolRepository.save(rolAdmin);
        }


        Role rolManager = rolRepository.findRoleByName("ROLE_MANAGER");
        if (rolManager == null) {
            rolManager = new Role();
            rolManager.setName("ROLE_MANAGER");
            rolRepository.save(rolManager);
        }

        Role roleOwner = rolRepository.findRoleByName("ROLE_OWNER");
        if (roleOwner == null) {
            roleOwner = new Role();
            roleOwner.setName("ROLE_OWNER");
            rolRepository.save(roleOwner);
        }

        Role rolSocio = rolRepository.findRoleByName("ROLE_SOCIO");
        if (rolSocio == null) {
            rolSocio = new Role();
            rolSocio.setName("ROLE_SOCIO");
            rolRepository.save(rolSocio);
        }



        User usuario1=usuarioRepository.findByUsername("admin@clubnautico.com");
        if (usuario1==null){
            User admin = new User();
            admin.setEmail("admin@clubnautico.com");
            admin.setUsername("admin@clubnautico.com");
            admin.setPassword( new BCryptPasswordEncoder().encode("Asdf1234!"));
            admin.getRoles().add(rolAdmin);
            usuarioRepository.save(admin);
        }

        User usuario2=usuarioRepository.findByUsername("manager@clubnautico.com");
        if (usuario2==null) {
            User user = new User();
            user.setEmail("manager@clubnautico.com");
            user.setUsername("manager@clubnautico.com");
            user.setPassword(new BCryptPasswordEncoder().encode("Asdf1324!"));
            user.getRoles().add(rolManager);
            usuarioRepository.save(user);
        }

        User usuario3=usuarioRepository.findByUsername("user@clubnautico.com");
        if (usuario3==null) {
            User user = new User();
            user.setEmail("user@clubnautico.com");
            user.setUsername("user@clubnautico.com");
            user.setPassword(new BCryptPasswordEncoder().encode("Asdf1324!"));
            user.getRoles().add(roleOwner);
            usuarioRepository.save(user);
        }

        User usuario4=usuarioRepository.findByUsername("neo@clubnautico.com");
        if (usuario4==null) {
            User user = new User();
            user.setEmail("neo@clubnautico.com");
            user.setUsername("neo@clubnautico.com");
            user.setPassword(new BCryptPasswordEncoder().encode("Asdf1324!"));
            user.getRoles().add(rolSocio);
            usuarioRepository.save(user);
        }

    }
}
