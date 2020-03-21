package pl.itj.dev.services.authservice.domain.security;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "ROLES")
@Data
public class Role {

    @Id
    private Long id;

    @Enumerated(EnumType.STRING)
    private RoleClass name;


}
