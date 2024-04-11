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
    private String nameenrollment;

    @NonNull
    private String name;

    private int numberberth;

    private int fee;

    @NonNull
    private String[] usernames;
}
