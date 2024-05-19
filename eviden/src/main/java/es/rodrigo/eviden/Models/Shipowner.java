package es.rodrigo.eviden.Models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import es.rodrigo.eviden.security.ModelSecurity.User;
import jakarta.persistence.*;
import lombok.*;
import java.util.HashSet;
import java.util.List;
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

    @Column(name = "country")
    private String country;

    //relaciones

    //SHIPOWNER - barco
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "shipowner_boat",
            joinColumns = @JoinColumn(name = "shipowner_id"),
            inverseJoinColumns = @JoinColumn(name = "boat_id"))
    private Set<Boat> boats = new HashSet<>();

    //User - SHIPOWNER
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    //Departure - SHIPOWNER
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "shipowner")
    @JsonIgnore
    private List<Departure> departures;
}
