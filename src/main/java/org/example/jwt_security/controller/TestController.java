package org.example.jwt_security.controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class TestController {

    @GetMapping("/home")
    public String home() {
        return "JWT Working Successfully!";
    }
}