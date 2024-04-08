package es.rodrigo.eviden.Services;

import es.rodrigo.eviden.Interfaces.IShipownerInterface;
import es.rodrigo.eviden.Models.Shipowner;
import es.rodrigo.eviden.Repositories.IShipownerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IShipownerService implements IShipownerInterface {

    @Autowired
    IShipownerRepository iShipownerRepository;

    @Override
    public List<Shipowner> getAll() {
        return iShipownerRepository.findAll();
    }

    @Override
    public Shipowner getById(int id) {
        return iShipownerRepository.getReferenceById(id);
    }

    @Override
    public void deleteShipowner(int id) {
        iShipownerRepository.deleteById(id);
    }

    @Override
    public void createShipowner(Shipowner shipowner) {
        iShipownerRepository.save(shipowner);
    }
}
