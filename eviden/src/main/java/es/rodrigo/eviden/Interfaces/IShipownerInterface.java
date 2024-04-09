package es.rodrigo.eviden.Interfaces;

import es.rodrigo.eviden.Models.Shipowner;

import java.util.List;

public interface IShipownerInterface {
    List<Shipowner> getAll();
    Shipowner getById(int id);
    Shipowner getByUsername(String username);
    void deleteShipowner(int id);
    void createShipowner(Shipowner shipowner);
}
