package pl.itj.dev.services.authservice.controllers;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import pl.itj.dev.services.authservice.domain.auth.CreatedUserData;
import pl.itj.dev.services.authservice.domain.auth.JWTResponse;
import pl.itj.dev.services.authservice.domain.auth.SignUpData;
import pl.itj.dev.services.authservice.domain.auth.UserLoginCredentials;
import pl.itj.dev.services.authservice.exceptions.CannotCreateUserException;
import pl.itj.dev.services.authservice.services.ifc.JWTService;
import pl.itj.dev.services.authservice.services.ifc.UserService;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthController {

    AuthenticationManager authenticationManager;
    UserDetailsService userDetailsService;
    JWTService jwtService;
    UserService userService;

    @GetMapping(value = "/authenticate")
    public ResponseEntity<JWTResponse> authenticate(@RequestBody UserLoginCredentials userLoginCredentials) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userLoginCredentials.getUsername(), userLoginCredentials.getPassword());
        authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        UserDetails user = userDetailsService.loadUserByUsername(userLoginCredentials.getUsername());
        return ResponseEntity.ok(new JWTResponse(jwtService.generateToken(user).orElse("")));
    }

    @PostMapping(value = "/sign-up")
    public ResponseEntity<CreatedUserData> signUp(@RequestBody SignUpData signUpData) throws CannotCreateUserException {
        Optional<CreatedUserData> createdUserData = userService.createUser(signUpData);
        CreatedUserData createdUser = createdUserData.orElseThrow(() -> new CannotCreateUserException(signUpData.getUsername()));
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }
}
