package pl.itj.dev.services.authservice.domain;

import lombok.Data;
import pl.itj.dev.services.authservice.domain.security.Role;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "USERS")
@Data
public class User {

    @Id
    private Long id;
    private String userName;
    private String email;
    private String password;

    @OneToMany
    private Set<Role> roles;

}
