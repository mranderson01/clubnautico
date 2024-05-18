package es.rodrigo.eviden.Interfaces;

import es.rodrigo.eviden.security.ModelSecurity.User;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

public interface IUser {
      ResponseEntity<?> obtenerPorUsuario(String usuario);
      ResponseEntity<?> guardarUsuario(User usuario);
      ResponseEntity<?> obtenerUsuarios();

      ResponseEntity<?> encontrarPorId(int id);
      ResponseEntity<?> eliminarUserPorId(int id);
      ResponseEntity<?> eliminarUsuario(User usuarioBorrar);
}