package es.rodrigo.eviden.Services;

import es.rodrigo.eviden.Interfaces.IHomeInterface;
import org.springframework.stereotype.Service;

@Service
public class IHomeService implements IHomeInterface {
    @Override
    public String obtenerMensaje() {

        String informacion;
        return "Se recibira datos para ir a home";
    }
}
