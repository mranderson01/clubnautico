package es.rodrigo.eviden.security.Services;

import es.rodrigo.eviden.security.Interface.IRole;
import es.rodrigo.eviden.security.Models.Role;
import es.rodrigo.eviden.security.repositories.IRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RolServiceImpl implements IRole {
    @Autowired
    IRoleRepository irolerepository;
    @Override
    public Role obtenerRol(String role) {
        return irolerepository.findRoleByName(role);
    }
}
