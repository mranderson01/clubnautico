package es.rodrigo.eviden.Services;

import es.rodrigo.eviden.Models.Boat;
import es.rodrigo.eviden.Models.Departure;
import es.rodrigo.eviden.Models.Partner;
import es.rodrigo.eviden.Models.Shipowner;
import es.rodrigo.eviden.Repositories.IBoatRepository;
import es.rodrigo.eviden.Repositories.IDepartureRepository;
import es.rodrigo.eviden.Repositories.IPartnerRepository;
import es.rodrigo.eviden.Repositories.IShipownerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DataInicialSeeder implements CommandLineRunner {

    @Autowired
    private IBoatRepository iBoatRepository;
    @Autowired
    private IDepartureRepository iDepartureRepository;
    @Autowired
    private IPartnerRepository iPartnerRepository;
    @Autowired
    private IShipownerRepository iShipownerRepository;


    @Override
    public void run(String... args) throws Exception {

        //Socios
        Partner partner1 = iPartnerRepository.findByUsername("neo@clubnautico.com");
        if (partner1==null){
            partner1 = new Partner();
            partner1.setUsername("neo@clubnautico.com");
            partner1.setName("Thomas");
            partner1.setSurname("Anderson");
            partner1.setCountry("EEUU");
            iPartnerRepository.save(partner1);
        }

        Partner partner2 = iPartnerRepository.findByUsername("morfeo@clubnautico.com");
        if (partner2==null){
            partner2 = new Partner();
            partner2.setUsername("morfeo@clubnautico.com");
            partner2.setName("unknow");
            partner2.setSurname("unknow");
            partner2.setCountry("unknow");
            iPartnerRepository.save(partner2);
        }

        Partner partner3 = iPartnerRepository.findByUsername("trinity@clubnautico.com");
        if (partner3==null){
            partner3 = new Partner();
            partner3.setUsername("trinity@clubnautico.com");
            partner3.setName("unknow");
            partner3.setSurname("unknow");
            partner3.setCountry("unknow");
            iPartnerRepository.save(partner3);
        }

        //Botes
        Boat boat1 = iBoatRepository.findByNumberberth(1);
        if (boat1==null){
            boat1 = new Boat();
            boat1.setNameenrollment("Titanic");
            boat1.setName("Titanic");
            boat1.setNumberberth(1);
            boat1.setFee(1500);
            iBoatRepository.save(boat1);
        }

        Boat boat2 = iBoatRepository.findByNumberberth(2);
        if (boat2==null){
            boat2 = new Boat();
            boat2.setNameenrollment("Neptune");
            boat2.setName("Neptune");
            boat2.setNumberberth(2);
            boat2.setFee(1500);
            iBoatRepository.save(boat2);
        }

        Boat boat3 = iBoatRepository.findByNumberberth(3);
        if (boat3 == null){
            boat3 = new Boat();
            boat3.setNameenrollment("Mars");
            boat3.setName("Mars");
            boat3.setNumberberth(3);
            boat3.setFee(1500);
            iBoatRepository.save(boat3);
        }

        //Propietarios
        List<Shipowner> listShipowner = new ArrayList<>();

        Shipowner shipowner1 = iShipownerRepository.findByUsername("shipowner1@clubnautico.com");
        if (shipowner1==null){
            shipowner1 = new Shipowner();
            shipowner1.setUsername("shipowner1@clubnautico.com");
            shipowner1.setName("shipowner1Name");
            shipowner1.setSurname("shipowner1Surname");
            shipowner1.setCountry("shipowner1Country");
            shipowner1.setDni("12345678X");
            shipowner1.setPhone("123456789");
            shipowner1.getBoats().add(boat1);
            listShipowner.add(shipowner1);
        }

        Shipowner shipowner2 = iShipownerRepository.findByUsername("shipowner2@clubnautico.com");
        if (shipowner2==null){
            shipowner2 = new Shipowner();
            shipowner2.setUsername("shipowner2@clubnautico.com");
            shipowner2.setName("shipowner2Name");
            shipowner2.setSurname("shipowner2Surname");
            shipowner2.setCountry("shipowner2Country");
            shipowner2.setDni("12345678Y");
            shipowner2.setPhone("123456789");
            shipowner2.getBoats().add(boat2);
            listShipowner.add(shipowner2);
        }

        Shipowner shipowner3 = iShipownerRepository.findByUsername("shipowner3@clubnautico.com");
        if (shipowner3==null){
            shipowner3 = new Shipowner();
            shipowner3.setUsername("shipowner3@clubnautico.com");
            shipowner3.setName("shipowner3Name");
            shipowner3.setSurname("shipowner3Surname");
            shipowner3.setCountry("shipowner3Country");
            shipowner3.setDni("12345678Z");
            shipowner3.setPhone("123456789");
            shipowner3.getBoats().add(boat3);
            listShipowner.add(shipowner3);
        }
        iShipownerRepository.saveAll(listShipowner);


        //Salidas
        Optional<Departure> departure1 = iDepartureRepository.findById(1);
        Boat finalBoat = boat1;
        if (departure1.isEmpty()){
            Departure newDeparture = new Departure();
            newDeparture.setDate(new Date());
            newDeparture.setTime("31/01/2024");
            newDeparture.setDestination("menorca");
            newDeparture.setBoat(finalBoat);
            iDepartureRepository.save(newDeparture);
        }

        Optional<Departure> departure2 = iDepartureRepository.findById(2);
        Boat finalBoat2 = boat2;
        if (departure2.isEmpty()){
            Departure newDeparture = new Departure();
            newDeparture.setDate(new Date());
            newDeparture.setTime("31/01/2024");
            newDeparture.setDestination("Mallorca");
            newDeparture.setBoat(finalBoat2);
            iDepartureRepository.save(newDeparture);
        }


        Optional<Departure> departure3 = iDepartureRepository.findById(3);
        Boat finalBoat3 = boat3;
        if (departure3.isEmpty()){
            Departure newDeparture = new Departure();
            newDeparture.setDate(new Date());
            newDeparture.setTime("31/01/2024");
            newDeparture.setDestination("Ibiza");
            newDeparture.setBoat(finalBoat3);
            iDepartureRepository.save(newDeparture);
        }

    }
}
