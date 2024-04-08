package es.rodrigo.eviden.Models;


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
    @ManyToMany
    @JoinTable(
            name = "shipowner_boat",
            joinColumns = @JoinColumn(name = "shipowner_id"),
            inverseJoinColumns = @JoinColumn(name = "boat_id"))
    private Set<Boat> boats = new HashSet<>();
}
