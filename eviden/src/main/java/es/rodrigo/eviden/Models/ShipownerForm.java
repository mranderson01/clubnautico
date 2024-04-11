package es.rodrigo.eviden.Models;

import jakarta.persistence.Column;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class ShipownerForm {
    private String username;

    private String name;

    private String surname;

    private String country;

    private String dni;

    private String phone;
}
