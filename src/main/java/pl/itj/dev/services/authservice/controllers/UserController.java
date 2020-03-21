package pl.itj.dev.services.authservice.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping(value = "/test")
    public String testEndpoint() {
        return "test";
    }

}
