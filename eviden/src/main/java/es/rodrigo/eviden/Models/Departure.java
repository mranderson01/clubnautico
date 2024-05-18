package es.rodrigo.eviden.Models;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "departure")
public class Departure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "date")
    private Date date;

    @Column(name = "time")
    private  String time;

    @Column(name = "destination")
    private  String destination;

    //propiedades

    //BOAT - DEPARTURE
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "boat_id")
    private Boat boat;

    //DEPARTURE - Shipowner
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "shipowner_id")
    private Shipowner shipowner;


}
