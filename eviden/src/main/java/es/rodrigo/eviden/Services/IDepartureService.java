package es.rodrigo.eviden.Services;

import es.rodrigo.eviden.Interfaces.IDepartureInterface;
import es.rodrigo.eviden.Models.Departure;
import es.rodrigo.eviden.Repositories.IDepartureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IDepartureService implements IDepartureInterface {

    @Autowired
    IDepartureRepository iDepartureRepository;
    @Override
    public List<Departure> getAll() {
        return iDepartureRepository.findAll();
    }

    @Override
    public Optional<Departure> getById(int id) {
        return iDepartureRepository.findById(id);
    }

    @Override
    public void createDeparture(Departure departure) {
        iDepartureRepository.save(departure);
    }

    @Override
    public void deleteDeparture(int id) {
        iDepartureRepository.deleteById(id);
    }
}
