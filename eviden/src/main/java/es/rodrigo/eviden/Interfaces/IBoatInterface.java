package es.rodrigo.eviden.Interfaces;

import es.rodrigo.eviden.Models.Boat;

import java.util.List;

public interface IBoatImpl {
    List<Boat> getAll();
    Boat findByName(String Name);
    Boat findByNameenrollment(String Nameenrollment);
    Boat findbyNumberberth(int Numberberth);

    void createBoat(Boat boat);
    void deleteBoat(int id);
}
