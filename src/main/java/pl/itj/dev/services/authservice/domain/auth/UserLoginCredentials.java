package pl.itj.dev.services.authservice.domain.auth;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserLoginCredentials {
    String username;
    String password;
}
