package es.rodrigo.eviden.Models;

import jakarta.persistence.Column;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
@Setter
@Getter
public class DepartureForm {

    private Date date;
    private  String time;
    private  String destination;

}
