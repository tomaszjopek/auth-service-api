package pl.itj.dev.services.authservice.services.ifc;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface JWTService {

    Optional<String> generateToken(UserDetails user);

    boolean validateToken(String token);

}
