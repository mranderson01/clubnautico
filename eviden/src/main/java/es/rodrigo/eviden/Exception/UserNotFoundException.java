package es.rodrigo.eviden.Exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(Integer id) {
        super("Usuario no encontrado " + id);
    }
}
