package es.rodrigo.eviden.Interfaces;

import es.rodrigo.eviden.Models.Shipowner;

import java.util.List;

public interface IShipownerImpl {
    List<Shipowner> getAll();
    Shipowner getById(int id);
    void deleteShipowner(int id);
    void createShipowner(Shipowner shipowner);
}
