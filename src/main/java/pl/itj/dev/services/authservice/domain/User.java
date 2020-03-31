package pl.itj.dev.services.authservice.domain;

import lombok.*;
import pl.itj.dev.services.authservice.domain.security.Role;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "USERS")
@Data
@Builder(builderClassName = "UserBuilder", access = AccessLevel.PUBLIC)
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_sequence")
    @SequenceGenerator(name = "users_sequence", allocationSize = 1, sequenceName = "USERS_SEQ")
    private Long id;
    private String userName;
    private String email;
    private String password;

    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Role> roles;
}
