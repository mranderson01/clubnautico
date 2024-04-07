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

import java.util.Date;
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
            partner1.setUsername("neo");
            partner1.setName("Thomas");
            partner1.setSurname("Anderson");
            partner1.setCountry("EEUU");
        }

        Partner partner2 = iPartnerRepository.findByUsername("morfeo@clubnautico.com");
        if (partner1==null){
            partner2 = new Partner();
            partner2.setUsername("morfeo");
            partner2.setName("unknow");
            partner2.setSurname("unknow");
            partner2.setCountry("unknow");
        }

        Partner partner3 = iPartnerRepository.findByUsername("trinity@clubnautico.com");
        if (partner3==null){
            partner3 = new Partner();
            partner3.setUsername("trinity");
            partner3.setName("unknow");
            partner3.setSurname("unknow");
            partner3.setCountry("unknow");
        }

        //Propietarios
        Shipowner shipowner1 = iShipownerRepository.findByUsername("shipowner1@clubnautico.com");
        if (shipowner1==null){
            shipowner1 = new Shipowner();
            shipowner1.setUsername("shipowner1");
            shipowner1.setName("shipowner1Name");
            shipowner1.setSurname("shipowner1Surname");
            shipowner1.setCountry("shipowner1Country");
            shipowner1.setDni("12345678X");
            shipowner1.setPhone("123456789");
        }
        Shipowner shipowner2 = iShipownerRepository.findByUsername("shipowner2@clubnautico.com");
        if (shipowner2==null){
            shipowner2 = new Shipowner();
            shipowner2.setUsername("shipowner2");
            shipowner2.setName("shipowner2Name");
            shipowner2.setSurname("shipowner2Surname");
            shipowner2.setCountry("shipowner2Country");
            shipowner2.setDni("12345678Y");
            shipowner2.setPhone("123456789");
        }
        Shipowner shipowner3 = iShipownerRepository.findByUsername("shipowner3@clubnautico.com");
        if (shipowner3==null){
            shipowner3 = new Shipowner();
            shipowner3.setUsername("shipowner3");
            shipowner3.setName("shipowner3Name");
            shipowner3.setSurname("shipowner3Surname");
            shipowner3.setCountry("shipowner3Country");
            shipowner3.setDni("12345678Z");
            shipowner3.setPhone("123456789");
        }

        //Botes

        Boat boat1 = iBoatRepository.findByNumberberth(1);
        if (boat1==null){
            boat1 = new Boat();
            boat1.setNameenrollment("Titanic");
            boat1.setName("Titanic");
            boat1.setNumberberth(1);
            boat1.setFee(1500);
        }
        Boat boat2 = iBoatRepository.findByNumberberth(2);
        if (boat2==null){
            boat2 = new Boat();
            boat2.setNameenrollment("Neptune");
            boat2.setName("Neptune");
            boat2.setNumberberth(2);
            boat2.setFee(1500);
        }
        Boat boat3 = iBoatRepository.findByNumberberth(3);
        if (boat3 == null){
            boat3 = new Boat();
            boat3.setNameenrollment("Mars");
            boat3.setName("Mars");
            boat3.setNumberberth(3);
            boat3.setFee(1500);
        }

        //Salidas
        Departure departure1 = iDepartureRepository.findById(1);
        if(departure1==null){
            departure1.setDate(new Date());
            departure1.setTime("31/01/2024");
            departure1.setDestination("Mallorca");
        }
        Departure departure2 = iDepartureRepository.findById(1);
        if(departure2==null){
            departure2.setDate(new Date());
            departure2.setTime("31/01/2024");
            departure2.setDestination("Ibiza");
        }
        Departure departure3 = iDepartureRepository.findById(1);
        if(departure3==null){
            departure3.setDate(new Date());
            departure3.setTime("31/01/2024");
            departure3.setDestination("Canarias");
        }
    }
}
