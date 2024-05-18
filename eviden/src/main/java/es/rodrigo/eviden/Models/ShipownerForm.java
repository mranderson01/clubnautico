package es.rodrigo.eviden.Models;

import jakarta.persistence.Column;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class ShipownerForm {

    private String country;
    private String dni;
    private int idUser;
}
