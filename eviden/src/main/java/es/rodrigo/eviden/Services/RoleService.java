package es.rodrigo.eviden.Services;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import es.rodrigo.eviden.Models.Role;
import es.rodrigo.eviden.Repositories.IRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RoleService {
    @Autowired
    private IRoleRepository roleRepo;

    public void Create(Role createRole){
        roleRepo.saveAndFlush(createRole);
    }

    public List<Role> ReadAll(){
        Iterable<Role> iterRole = roleRepo.findAll();

        List<Role> listRole = StreamSupport
                .stream(iterRole.spliterator(), false)
                .toList();

        return listRole;
    }

    public Role ReadSingle(Integer idRole){
        return GetRole(idRole);
    }

    public void Update(Role RoleUpdate){
        Role oldRole = GetRole(RoleUpdate.getRole_id());

        oldRole.setNombre(RoleUpdate.getNombre());

        roleRepo.saveAndFlush(oldRole);
    }

    public void Delete(Integer idRole){
        roleRepo.deleteById(idRole);
        roleRepo.flush();
    }


    public Boolean Exist(Integer idRole){
        return roleRepo.existsById(idRole);
    }

    public Role GetRole(Integer idRole){
        Role RoleRead = null;
        Optional<Role> RoleFound = roleRepo.findById(idRole);

        if (RoleFound.isPresent()) {
            RoleRead = RoleFound.get();
        }

        return RoleRead;
    }

    public void Save(List<Role> roleList) {
        roleRepo.saveAllAndFlush(roleList);
    }
}
