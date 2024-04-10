package es.rodrigo.eviden.Services;

import es.rodrigo.eviden.Models.Role;
import es.rodrigo.eviden.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;


public class SeederUsersRoles {

        @Autowired
        private UserService userService;

        @Autowired
        private RoleService roleService;

        @Bean
        public Boolean Seed(){
            boolean seedingCorrect = false;
            List<User> theUserList = userService.ReadAll();
            List<Role> theRoleList = roleService.ReadAll();



            if (theRoleList.isEmpty()) {
                theRoleList = this.TheRoleList();
                roleService.Save(theRoleList);

                seedingCorrect = true;
            }

            if (theUserList.isEmpty()) {
                theUserList = this.TheUserList(theRoleList);
                userService.Save(theUserList);

                seedingCorrect = true;
            }


            return seedingCorrect;
        }

        private List<User> TheUserList(List<Role> roleList) {
            List<User> userList = new ArrayList<>();
            List<Role> roleListAdd = new ArrayList<>();

            String correo = "@clubnautico.com";
            String defaultPassword = new BCryptPasswordEncoder().encode("Asdf1234!");

            //Admin Seed
            String nombreUsuario = "admin";
            roleListAdd.add(roleList.get(0));

            User adminUser = new User(1, nombreUsuario + correo, nombreUsuario, "deault", defaultPassword, roleListAdd);
            roleListAdd.clear();

            //Manager Seed
            nombreUsuario = "manager";
            roleListAdd.add(roleList.get(1));

            User managerUser = new User(2, nombreUsuario + correo, nombreUsuario, "deault", defaultPassword, roleListAdd);
            roleListAdd.clear();

            //User Seed
            nombreUsuario = "user";
            roleListAdd.add(roleList.get(2));

            User basicUser = new User(3, nombreUsuario + correo, nombreUsuario, "deault", defaultPassword, roleListAdd);

            userList.add(adminUser);
            userList.add(managerUser);
            userList.add(basicUser);

            return userList;
        }

        private List<Role> TheRoleList(){
            List<Role> roleList = new ArrayList<>();

            String prefijo = "ROLE_";
            List<String> roleName = new ArrayList<>();
            //Add a Role in UpperCase
            roleName.add(prefijo + "ADMIN");
            roleName.add(prefijo + "MANAGER");
            roleName.add(prefijo + "USER");

            Role newRoles = null;
            for (int index = 0; index < roleName.size(); index++) {
                newRoles = new Role((index + 1), roleName.get(index), new ArrayList<>());
                roleList.add(newRoles);
            }

            return roleList;
        }
}
