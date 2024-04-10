package es.rodrigo.eviden.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import es.rodrigo.eviden.Models.Role;
import es.rodrigo.eviden.Models.User;
import es.rodrigo.eviden.Repositories.IRoleRepository;
import es.rodrigo.eviden.Repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@Primary
public class UserService implements UserDetailsService{
    @Autowired
    private IUserRepository userRepo;
    @Autowired
    private RoleService roleSvc;

    @Autowired
    private IRoleRepository roleRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        boolean exist = this.Exist(username);
        if (exist) {
            User loadUser = GetUser(username);

            String authorities = "";
            List<Role> roleUserList = loadUser.getRoles();
            List<GrantedAuthority> authorities2 = roleUserList.stream()
                    .map(role -> new SimpleGrantedAuthority(role.getNombre()))
                    .collect(Collectors.toList());
            UserDetails userDetails =
                    org.springframework.security.core.userdetails.User
                            .withUsername(loadUser.getUsername())
                            .password(loadUser.getPassword())
                            .authorities(authorities2)
                            .build();

            return userDetails;
        }

        throw new UsernameNotFoundException("Usuario no encontrado con nombre de usuario: " + username);
    }

    public void Create(User createUser){
        //Encoding the User Password
        String userPsw = new BCryptPasswordEncoder().encode(createUser.getPassword());
        createUser.setPassword(userPsw);

        //Creating the Role if not exist
        String basicRole = "ROLE_USER";
        Optional<Role> userRole = roleRepo.findByNombre(basicRole);
        if (!userRole.isPresent()) {
            Role newRole = new Role((Integer)null, basicRole, new ArrayList<>());
            roleSvc.Create(newRole);

            //Created and Extracted (again)
            userRole = roleRepo.findByNombre(basicRole);
        }

        //Setting the Basic Role to the New User
        List<Role> newRoleList = new ArrayList<>();
        newRoleList.add(userRole.get());
        createUser.setRoles(newRoleList);

        userRepo.saveAndFlush(createUser);
    }

    public List<User> ReadAll(){
        Iterable<User> iterUser = userRepo.findAll();

        List<User> listUser = StreamSupport
                .stream(iterUser.spliterator(), false)
                .toList();

        return listUser;
    }

    public User ReadSingle(String username){
        return GetUser(username);
    }

    public void Update(User userUpdate){
        // User oldUser = GetUser(UserUpdate.getUser_id());

        userRepo.saveAndFlush(userUpdate);
    }

    public void Delete(Integer idUser){
        userRepo.deleteById(idUser);
        userRepo.flush();
    }


    public Boolean Exist(String username){
        return userRepo.existsByUsername(username);
    }

    public User GetUser(String username) {
        return userRepo.findByUsername(username).get();
    }

    public void Save(User user) {
        userRepo.saveAndFlush(user);
    }

    public void Save(List<User> userList) {
        userRepo.saveAllAndFlush(userList);
    }



}
