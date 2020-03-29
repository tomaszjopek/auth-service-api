package pl.itj.dev.services.authservice.exceptions;

public class CannotCreateUserException extends Exception {
    public CannotCreateUserException(String username) {
        super("Cannot create user: " +  username);
    }
}
