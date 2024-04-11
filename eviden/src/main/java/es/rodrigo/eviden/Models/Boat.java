package es.rodrigo.eviden.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "nameenrollment")
    private String nameenrollment;

    @NotNull
    @Column(name = "numberberth")
    private int numberberth;

    @NotNull
    @Column(name = "fee")
    private int fee;

    //relaciones
    //barco - Propietario
    @ManyToMany(mappedBy = "boats", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Shipowner> Shipowners = new HashSet<>();


    //BOAT - DEPARTURE
    @OneToMany(mappedBy = "boat",cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Departure> departures = new HashSet<>();
}
