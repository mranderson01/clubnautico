package es.rodrigo.eviden.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user",uniqueConstraints = {@UniqueConstraint(columnNames = {"username"})})
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int user_id;

    @Column(nullable = false)
    private String username;

    private String firstname;

    private String lastname;

    private String password;

    //Relationships

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "Users_Roles",
            joinColumns = { @JoinColumn(name = "user_Id")},
            inverseJoinColumns = { @JoinColumn(name = "role_Id")}
    )
    private List<Role> roles = new ArrayList<Role>();

    //Overriden Methods
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> theAuthorities = new ArrayList<>();

        //Agregando cada "Authority" según el rol
        this.roles.forEach(role ->
                theAuthorities.add(new SimpleGrantedAuthority(
                        role.getNombre()
                ))
        );

        return theAuthorities;
    }
}
