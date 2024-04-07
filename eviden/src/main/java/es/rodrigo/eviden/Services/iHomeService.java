package es.rodrigo.eviden.Services;

import es.rodrigo.eviden.Interfaces.IHomeImpl;

public class iHomeService implements IHomeImpl {
    @Override
    public String obtenerMensaje() {

        String informacion;
        return "Se recibira datos para ir a home";
    }
}
