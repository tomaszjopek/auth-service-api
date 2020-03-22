package pl.itj.dev.services.authservice.controllers;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.itj.dev.services.authservice.domain.auth.UserLoginCredentials;
import pl.itj.dev.services.authservice.services.ifc.JWTService;

@RestController
@RequestMapping("/v1")
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {

    AuthenticationManager authenticationManager;
    UserDetailsService userDetailsService;
    JWTService jwtService;

    @GetMapping(value = "/authenticate")
    public ResponseEntity<String> authenticate(@RequestBody UserLoginCredentials userLoginCredentials) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userLoginCredentials.getUsername(), userLoginCredentials.getPassword());
        authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        UserDetails user = userDetailsService.loadUserByUsername(userLoginCredentials.getUsername());
        return ResponseEntity.ok(jwtService.generateToken(user).orElse(""));
    }

}
