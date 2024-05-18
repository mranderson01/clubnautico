package es.rodrigo.eviden.Models;

import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Data
@Setter
@Getter
public class CreationBoatForm {

    @NonNull
    private String name;

    @NonNull
    private String nameenrollment;

    private int numberberth;

    private int fee;

    private int idShipowner;

}
