package es.rodrigo.eviden.Services;

import es.rodrigo.eviden.Repositories.IRoleRepository;
import es.rodrigo.eviden.security.ModelSecurity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class RoleService {
    @Autowired
    private IRoleRepository roleRepository;

    public Role ReadSingle(Integer idRole){
        return GetRole(idRole);
    }

    public Role GetRole(Integer idRole){
        Role RoleRead = null;
        Optional<Role> RoleFound = roleRepository.findById(idRole);

        if (RoleFound.isPresent()) {
            RoleRead = RoleFound.get();
        }

        return RoleRead;
    }
}
