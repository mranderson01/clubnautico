package es.rodrigo.eviden.Interfaces;

import es.rodrigo.eviden.Models.Departure;

import java.util.List;

public interface IDepartureImpl {
    List<Departure> getAll();
    Departure getById(int id);
    void createDeparture(Departure departure);
    void deleteDeparture(int id);
}
