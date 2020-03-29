package pl.itj.dev.services.authservice.services.ifc;

import pl.itj.dev.services.authservice.domain.auth.CreatedUserData;
import pl.itj.dev.services.authservice.domain.auth.SignUpData;

import java.util.Optional;

public interface UserService {

    Optional<CreatedUserData> createUser(SignUpData signUpData);

}
