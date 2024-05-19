package es.rodrigo.eviden.Services;

import es.rodrigo.eviden.Models.Boat;
import es.rodrigo.eviden.Models.Departure;
import es.rodrigo.eviden.Models.Shipowner;
import es.rodrigo.eviden.Repositories.*;
import es.rodrigo.eviden.security.ModelSecurity.Role;
import es.rodrigo.eviden.security.ModelSecurity.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DataInicialSeeder implements CommandLineRunner {

    @Autowired
    private IBoatRepository iBoatRepository;
    @Autowired
    private IDepartureRepository iDepartureRepository;
    @Autowired
    private IShipownerRepository iShipownerRepository;
    @Autowired
    private IUserRepository usuarioRepository;
    @Autowired
    private IRoleRepository rolRepository;


    @Override
    public void run(String... args) throws Exception {

        //ROLES
        List<Role> roles = new ArrayList<>();

        Optional<Role> rolAdmin = rolRepository.findRoleByName("ROLE_ADMIN");
        rolAdmin.ifPresent(roles::add);

        if (rolAdmin.isEmpty()) {
            Role rolAdmin1 = new Role();
            rolAdmin1.setName("ROLE_ADMIN");

            roles.add(rolAdmin1);
            rolRepository.save(rolAdmin1);
        }

        Optional<Role> roleOwner = rolRepository.findRoleByName("ROLE_OWNER");
        roleOwner.ifPresent(roles::add);
        if (roleOwner.isEmpty()) {
            Role rolManager1 = new Role();
            rolManager1.setName("ROLE_OWNER");
            roles.add(rolManager1);
            rolRepository.save(rolManager1);
        }

        Optional<Role> rolUser = rolRepository.findRoleByName("ROLE_USER");
        rolUser.ifPresent(roles::add);

        if (rolUser.isEmpty()) {
            Role rolUser1 = new Role();
            rolUser1.setName("ROLE_USER");
            roles.add(rolUser1);
            rolRepository.save(rolUser1);
        }

        Optional<Role> rolWorker = rolRepository.findRoleByName("ROLE_WORKER");
        rolWorker.ifPresent(roles::add);

        if (rolWorker.isEmpty()) {
            Role rolUser1 = new Role();
            rolUser1.setName("ROLE_WORKER");
            roles.add(rolUser1);
            rolRepository.save(rolUser1);
        }

        //USUARIOS

        List<User> users = new ArrayList<>();

        Optional<User> usuario1 = usuarioRepository.findByUsername("admin@clubnautico.com");
        usuario1.ifPresent(users::add);
        if (usuario1.isEmpty()){
            User admin = new User();
            admin.setFirstname("admin");
            admin.setLastname("admin");
            admin.setUsername("admin@clubnautico.com");
            admin.setPassword( new BCryptPasswordEncoder().encode("Asdf1234!"));
            admin.setPhone("123456789");
            admin.setDni("12345678A");

            users.add(admin);
            admin.getRoles().add(roles.getFirst());
            usuarioRepository.save(admin);
        }

        Optional<User> usuario2=usuarioRepository.findByUsername("owner@clubnautico.com");
        usuario2.ifPresent(users::add);
        if (usuario2.isEmpty()){
            User user = new User();
            user.setFirstname("owner");
            user.setLastname("owner");
            user.setUsername("owner@clubnautico.com");
            user.setPhone("123456789");
            user.setPassword(new BCryptPasswordEncoder().encode("Asdf1234!"));
            user.setDni("12345678B");


            users.add(user);
            user.getRoles().add(roles.get(1));
            usuarioRepository.save(user);
        }

        Optional<User> usuario3 = usuarioRepository.findByUsername("worker@clubnautico.com");
        usuario3.ifPresent(users::add);
        if (usuario3.isEmpty()){
            User user = new User();
            user.setFirstname("worker");
            user.setLastname("worker");
            user.setUsername("worker@clubnautico.com");
            user.setDni("12345678C");
            user.setPhone("123456789");
            user.setPassword(new BCryptPasswordEncoder().encode("Asdf1234!"));


            users.add(user);
            user.getRoles().add(roles.get(2));
            usuarioRepository.save(user);
        }

        Optional<User> usuario4 = usuarioRepository.findByUsername("user@clubnautico.com");
        usuario4.ifPresent(users::add);
        if (usuario4.isEmpty()){
            User user = new User();
            user.setFirstname("user");
            user.setLastname("user");
            user.setUsername("user@clubnautico.com");
            user.setPassword(new BCryptPasswordEncoder().encode("Asdf1234!"));
            user.setDni("12345678D");
            user.setPhone("123456789");

            users.add(user);
            user.getRoles().add(roles.get(3));
            usuarioRepository.save(user);
        }

        //Botes
        List<Boat> listBoat = new ArrayList<>();
        Optional<Boat> boat1 = iBoatRepository.findByNumberberth(1);
        boat1.ifPresent(listBoat::add);
        if(boat1.isEmpty()){
            Boat boat = new Boat();
            boat.setNameenrollment("Titanic");
            boat.setName("Titanic");
            boat.setNumberberth(1);
            boat.setFee(1500);

            listBoat.add(boat);
            iBoatRepository.save(boat);
        };

        Optional<Boat> boat2 = iBoatRepository.findByNumberberth(2);
        boat2.ifPresent(listBoat::add);
        if(boat2.isEmpty()){
            Boat boat = new Boat();
            boat.setNameenrollment("Neptune");
            boat.setName("Neptune");
            boat.setNumberberth(2);
            boat.setFee(1500);

            listBoat.add(boat);
            iBoatRepository.save(boat);
        };

        Optional<Boat> boat3 = iBoatRepository.findByNumberberth(3);
        boat3.ifPresent(listBoat::add);
        if(boat3.isEmpty()){
            Boat boat = new Boat();
            boat.setNameenrollment("Mars");
            boat.setName("Mars");
            boat.setNumberberth(3);
            boat.setFee(1500);

            listBoat.add(boat);
            iBoatRepository.save(boat);
        };

        //Propietarios

        List<Shipowner> listPropietarios =  new ArrayList<>();
        Optional<Shipowner> shipowner1 = Optional.ofNullable(iShipownerRepository.findByDni("12345678A"));
        shipowner1.ifPresent(listPropietarios::add);
        if (shipowner1.isEmpty()){
            Shipowner shipowner = new Shipowner();
            shipowner.setCountry("spain");

            shipowner.getBoats().add(listBoat.getFirst());

            shipowner.setUser(users.get(0));            
            listPropietarios.add(shipowner);
            iShipownerRepository.save(shipowner);
        }

        Optional<Shipowner> shipowner2 = Optional.ofNullable(iShipownerRepository.findByDni("12345678B"));
        shipowner1.ifPresent(listPropietarios::add);

        if (shipowner2.isEmpty()){
            Shipowner shipowner = new Shipowner();
            shipowner.setCountry("spain");
            shipowner.getBoats().add(listBoat.get(1));

            shipowner.setUser(users.get(1));
            listPropietarios.add(shipowner);
            iShipownerRepository.save(shipowner);
        }

        Optional<Shipowner> shipowner3 = Optional.ofNullable(iShipownerRepository.findByDni("12345678C"));
        shipowner1.ifPresent(listPropietarios::add);

        if (shipowner3.isEmpty()){
            Shipowner shipowner = new Shipowner();
            shipowner.setCountry("spain");
            shipowner.getBoats().add(listBoat.get(2));

            shipowner.setUser(users.get(2));
            listPropietarios.add(shipowner);
            iShipownerRepository.save(shipowner);
        }


        //Salidas
        Optional<Departure> departure1 = iDepartureRepository.findById(1);

        if (departure1.isEmpty()){
            Boat finalBoat = listBoat.get(0);
            Departure newDeparture = new Departure();
            newDeparture.setDate(new Date());
            newDeparture.setTime("31/01/2024");
            newDeparture.setDestination("menorca");
            newDeparture.setBoat(finalBoat);
            newDeparture.setShipowner(listPropietarios.get(0));
            iDepartureRepository.save(newDeparture);
        }

        Optional<Departure> departure2 = iDepartureRepository.findById(2);

        if (departure2.isEmpty()){
            Boat finalBoat2 = listBoat.get(1);
            Departure newDeparture = new Departure();
            newDeparture.setDate(new Date());
            newDeparture.setTime("31/01/2024");
            newDeparture.setDestination("Mallorca");
            newDeparture.setBoat(finalBoat2);
            newDeparture.setShipowner(listPropietarios.get(0));
            iDepartureRepository.save(newDeparture);
        }


        Optional<Departure> departure3 = iDepartureRepository.findById(3);

        if (departure3.isEmpty()){

            Boat finalBoat3 = listBoat.get(2);
            Departure newDeparture = new Departure();
            newDeparture.setDate(new Date());
            newDeparture.setTime("31/01/2024");
            newDeparture.setDestination("Ibiza");
            newDeparture.setShipowner(listPropietarios.get(0));
            newDeparture.setBoat(finalBoat3);

            iDepartureRepository.save(newDeparture);
        }

        Optional<Departure> departure4 = iDepartureRepository.findById(4);

        if (departure4.isEmpty()){
            Boat finalBoat = listBoat.get(0);
            Departure newDeparture = new Departure();
            newDeparture.setDate(new Date());
            newDeparture.setTime("31/01/2024");
            newDeparture.setDestination("Tenerife");
            newDeparture.setBoat(finalBoat);
            newDeparture.setShipowner(listPropietarios.get(1));
            iDepartureRepository.save(newDeparture);
        }

        Optional<Departure> departure5 = iDepartureRepository.findById(5);

        if (departure5.isEmpty()){
            Boat finalBoat2 = listBoat.get(1);
            Departure newDeparture = new Departure();
            newDeparture.setDate(new Date());
            newDeparture.setTime("31/01/2024");
            newDeparture.setDestination("Gran canaria");
            newDeparture.setBoat(finalBoat2);
            newDeparture.setShipowner(listPropietarios.get(1));
            iDepartureRepository.save(newDeparture);
        }


        Optional<Departure> departure6 = iDepartureRepository.findById(6);

        if (departure6.isEmpty()){

            Boat finalBoat3 = listBoat.get(2);
            Departure newDeparture = new Departure();
            newDeparture.setDate(new Date());
            newDeparture.setTime("31/01/2024");
            newDeparture.setDestination("Lanzarote");
            newDeparture.setShipowner(listPropietarios.get(1));
            newDeparture.setBoat(finalBoat3);

            iDepartureRepository.save(newDeparture);
        }

        Optional<Departure> departure7 = iDepartureRepository.findById(7);

        if (departure7.isEmpty()){
            Boat finalBoat = listBoat.get(0);
            Departure newDeparture = new Departure();
            newDeparture.setDate(new Date());
            newDeparture.setTime("31/01/2024");
            newDeparture.setDestination("hierro");
            newDeparture.setBoat(finalBoat);
            newDeparture.setShipowner(listPropietarios.get(2));
            iDepartureRepository.save(newDeparture);
        }

        Optional<Departure> departure8 = iDepartureRepository.findById(8);

        if (departure8.isEmpty()){
            Boat finalBoat2 = listBoat.get(1);
            Departure newDeparture = new Departure();
            newDeparture.setDate(new Date());
            newDeparture.setTime("31/01/2024");
            newDeparture.setDestination("Fuerteventura");
            newDeparture.setBoat(finalBoat2);
            newDeparture.setShipowner(listPropietarios.get(2));
            iDepartureRepository.save(newDeparture);
        }


        Optional<Departure> departure9 = iDepartureRepository.findById(9);

        if (departure9.isEmpty()){

            Boat finalBoat3 = listBoat.get(2);
            Departure newDeparture = new Departure();
            newDeparture.setDate(new Date());
            newDeparture.setTime("31/01/2024");
            newDeparture.setDestination("La Gomera");
            newDeparture.setShipowner(listPropietarios.get(2));
            newDeparture.setBoat(finalBoat3);

            iDepartureRepository.save(newDeparture);
        }

    }
}
