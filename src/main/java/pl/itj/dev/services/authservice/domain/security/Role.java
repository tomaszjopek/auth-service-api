package pl.itj.dev.services.authservice.domain.security;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "ROLES")
@Data
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_sequence")
    @SequenceGenerator(name = "role_sequence", allocationSize = 1, sequenceName = "ROLE_SEQ")
    private Long id;

    @Enumerated(EnumType.STRING)
    private RoleClass name;


}
