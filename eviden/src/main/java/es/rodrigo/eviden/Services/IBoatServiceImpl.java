package es.rodrigo.eviden.Services;

import es.rodrigo.eviden.Interfaces.IBoatInterface;
import es.rodrigo.eviden.Models.Boat;
import es.rodrigo.eviden.Repositories.IBoatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IBoatServiceImpl implements IBoatInterface {

    @Autowired
    IBoatRepository iBoatRepository;

    @Override
    public List<Boat> getAll() {
        return iBoatRepository.findAll();
    }

    @Override
    public Boat findByName(String Name) {
        return iBoatRepository.findByName(Name);
    }

    @Override
    public Boat findByNameenrollment(String Nameenrollment) {
        return iBoatRepository.findByNameEnrollment(Nameenrollment);
    }

    @Override
    public Boat findbyNumberberth(int Numberberth) {
        return iBoatRepository.findByNumberberth(Numberberth);
    }

    @Override
    public void createBoat(Boat boat) {
        iBoatRepository.save(boat);
    }

    @Override
    public void deleteBoat(int id) {
        iBoatRepository.deleteById(id);
    }
}
