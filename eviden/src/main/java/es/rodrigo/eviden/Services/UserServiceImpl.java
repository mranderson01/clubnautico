package es.rodrigo.eviden.Services;

import java.util.List; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus; 
import org.springframework.http.ResponseEntity; 
import org.springframework.stereotype.Service;

import es.rodrigo.eviden.Interfaces.IUser; 
import es.rodrigo.eviden.Repositories.IRoleRepository;
import es.rodrigo.eviden.Repositories.IUserRepository;
import es.rodrigo.eviden.security.ModelSecurity.User;


@Service
public class UserServiceImpl implements IUser{

    @Autowired
    private IUserRepository iUserRepository;
    @Autowired
    private RoleService roleSvc;

    @Autowired
    private IRoleRepository roleRepo;


    @Override
    public ResponseEntity<?> obtenerPorUsuario(String usuario) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'obtenerPorUsuario'");
    }

    @Override
    public ResponseEntity<?> guardarUsuario(User usuario) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'guardarUsuario'");
    }

    @Override
    public ResponseEntity<?> obtenerUsuarios() {
        
        List<User> users = iUserRepository.findAll();
        
        if (users.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuarios no encontrados");
        }

        return ResponseEntity.status(HttpStatus.OK).body(users);
    
    }

    @Override
    public ResponseEntity<?> encontrarPorId(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'encontrarPorId'");
    }

    @Override
    public ResponseEntity<?> eliminarUserPorId(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'eliminarUserPorId'");
    }

    @Override
    public ResponseEntity<?> eliminarUsuario(User usuarioBorrar) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'eliminarUsuario'");
    }
    
}
