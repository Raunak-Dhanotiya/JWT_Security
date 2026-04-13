package org.example.jwt_security.controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
public class TestController {

    @GetMapping("/home")
    public String home(Principal principal) {
        return "Welcome " + principal.getName();
    }

    @GetMapping("/user")
    public String user() {
        return "User Access";
    }

    @GetMapping("/admin")
    public String admin() {
        return "Admin Access";
    }
}