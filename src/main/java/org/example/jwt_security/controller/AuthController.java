package org.example.jwt_security.controller;
import org.example.jwt_security.dto.AuthRequest;
import org.example.jwt_security.dto.AuthResponse;
import org.example.jwt_security.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService service;

    @PostMapping("/register")
    public String register(@RequestBody AuthRequest req) {
        return service.register(req.getUsername(), req.getPassword(), req.getRole());
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest req) {
        return new AuthResponse(service.login(req.getUsername(), req.getPassword()));
    }
}