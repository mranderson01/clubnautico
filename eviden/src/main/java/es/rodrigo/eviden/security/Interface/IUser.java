package es.rodrigo.eviden.security.Interface;



import es.rodrigo.eviden.security.Models.User;

import java.util.List;
import java.util.Optional;

public interface IUser {
    User obtenerPorUsuario(String usuario);
     void guardarUsuario(User usuario);
     List<User> obtenerUsuarios();

     Optional<User> encontrarPorId(int id);
     void eliminarUserPorId(int id);

    void eliminarUsuario(User usuarioBorrar);
}
