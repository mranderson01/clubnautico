package es.rodrigo.eviden.Models;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "boat")
public class Boat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "nameenrollment")
    private String nameenrollment;

    @Column(name = "name")
    private String name;

    @Column(name = "numberberth")
    private int numberberth;

    @Column(name = "fee")
    private int fee;

    //relaciones
    //barco - Propietario
    @ManyToMany(mappedBy = "boats")
    private Set<Shipowner> shipowners = new HashSet<>();


    //BOAT - DEPARTURE
    @OneToMany(mappedBy = "boat")
    private Set<Departure> departures = new HashSet<>();
}
