package pl.itj.dev.services.authservice.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USERS")
@Data
public class User {

    @Id
    private Long id;
    private String name;

}
