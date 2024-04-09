package es.rodrigo.eviden.Models;

import jakarta.persistence.Column;
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

    @NonNull
    private int numberberth;

    @NonNull
    private int fee;

    @NonNull
    private String[] usernames;
}
