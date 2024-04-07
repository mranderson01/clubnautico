package es.rodrigo.eviden.security.Services;

import es.rodrigo.eviden.security.Interface.IUser;
import es.rodrigo.eviden.security.Models.User;
import es.rodrigo.eviden.security.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements IUser {
    @Autowired
    IUserRepository iUserRepository;

    @Override
    public User obtenerPorUsuario(String usuario) {
        return iUserRepository.findByUsername(usuario);
    }

    @Override
    public void guardarUsuario(User usuario) {
        iUserRepository.saveAndFlush(usuario);
    }

    @Override
    public List<User> obtenerUsuarios() {
        return iUserRepository.findAll();
    }

    @Override
    public Optional<User> encontrarPorId(int id) {
        return iUserRepository.findById(id);
    }

    @Override
    public void eliminarUserPorId(int id) {

        iUserRepository.deleteById(id);
    }

    @Override
    public void eliminarUsuario(User usuarioBorrar) {
        iUserRepository.delete(usuarioBorrar);
    }
}
