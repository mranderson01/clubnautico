package es.rodrigo.eviden.Models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "shipowner_boat")
public class Shipownerboat {

    @Column(name = "shipowner_id")
    private int shipowner_id;

    @Column(name = "boat_id")
    private int boat_id;

}
