package es.rodrigo.eviden.Interfaces;

import es.rodrigo.eviden.Models.Departure;

import java.util.List;
import java.util.Optional;

public interface IDepartureInterface {
    List<Departure> getAll();
    Optional<Departure> getById(int id);
    void createDeparture(Departure departure);
    void deleteDeparture(int id);
}
