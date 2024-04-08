package es.rodrigo.eviden.Services;

import es.rodrigo.eviden.Interfaces.IShipownerInterface;
import es.rodrigo.eviden.Models.Shipowner;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IShipownerService implements IShipownerInterface {
    @Override
    public List<Shipowner> getAll() {
        return null;
    }

    @Override
    public Shipowner getById(int id) {
        return null;
    }

    @Override
    public void deleteShipowner(int id) {

    }

    @Override
    public void createShipowner(Shipowner shipowner) {

    }
}
