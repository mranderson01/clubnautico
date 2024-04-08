package es.rodrigo.eviden.Models;


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
    @ManyToOne
    @JoinColumn(name = "boat_id")
    private Boat boat;
}
