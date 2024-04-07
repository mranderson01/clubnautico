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
public class Shipowner extends Partner{

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
