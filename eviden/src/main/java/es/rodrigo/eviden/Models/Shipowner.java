package es.rodrigo.eviden.Models;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import java.util.HashSet;
import java.util.Set;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "shipowner")
public class Shipowner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "username")
    private String username;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "country")
    private String country;

    @Column(name = "dni")
    private String dni;

    @Column(name = "phone")
    private String phone;

    //relaciones
    //Propietario - barco
    @ManyToMany(mappedBy = "shipowners", cascade = CascadeType.ALL)
    private Set<Boat> boats = new HashSet<>();
}
