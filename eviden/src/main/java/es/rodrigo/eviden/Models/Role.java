package es.rodrigo.eviden.Models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Role",uniqueConstraints = {@UniqueConstraint(columnNames = {"Nombre"})})
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int role_id;

    @Column(nullable = false)
    private String nombre;

    //Relationships

    @ManyToMany(mappedBy = "roles")
    private List<User> users = new ArrayList<User>();
}
