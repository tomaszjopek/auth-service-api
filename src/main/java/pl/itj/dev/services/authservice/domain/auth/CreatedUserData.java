package pl.itj.dev.services.authservice.domain.auth;

import lombok.AccessLevel;
import lombok.Value;
import lombok.experimental.FieldDefaults;

@Value
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreatedUserData {
    String username;
    Boolean isCreated;
}