package es.rodrigo.eviden.Services;


import es.rodrigo.eviden.Models.Role;
import es.rodrigo.eviden.Models.User;
import es.rodrigo.eviden.Repositories.IRoleRepository;
import es.rodrigo.eviden.Repositories.IUserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
@Service
public class DataUserRolesSeeder implements CommandLineRunner {
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

        Role rolUser = rolRepository.findRoleByName("ROLE_USER");
        if (rolUser == null) {
            rolUser = new Role();
            rolUser.setName("ROLE_USER");
            rolRepository.save(rolUser);
        }



        User usuario1=usuarioRepository.findByUsername("admin@myikea.com");
        if (usuario1==null){
            User admin = new User();
            admin.setEmail("admin@myikea.com");
            admin.setUsername("admin@myikea.com");
            admin.setPassword( new BCryptPasswordEncoder().encode("Asdf1234!"));
            admin.getRoles().add(rolAdmin);
            usuarioRepository.save(admin);
        }

        User usuario2=usuarioRepository.findByUsername("manager@myikea.com");
        if (usuario2==null) {
            User user = new User();
            user.setEmail("manager@myikea.com");
            user.setUsername("manager@myikea.com");
            user.setPassword(new BCryptPasswordEncoder().encode("Asdf1324!"));
            user.getRoles().add(rolManager);
            usuarioRepository.save(user);
        }

        User usuario3=usuarioRepository.findByUsername("user@myikea.com");
        if (usuario3==null) {
            User user = new User();
            user.setEmail("user@myikea.com");
            user.setUsername("user@myikea.com");
            user.setPassword(new BCryptPasswordEncoder().encode("Asdf1324!"));
            user.getRoles().add(rolUser);
            usuarioRepository.save(user);
        }
    }
}
