package pl.itj.dev.services.authservice.advices;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.itj.dev.services.authservice.domain.error.ErrorResponse;

@RestControllerAdvice
public class AuthControllerAdvisor {

    @ExceptionHandler(value = {UsernameNotFoundException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponse usernameNotFound(Exception ex) {
        return new ErrorResponse(HttpStatus.UNAUTHORIZED.value(), "The username or password cannot be found.");
    }
}
