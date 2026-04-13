package org.example.jwt_security.service;

import org.example.jwt_security.entity.User;
import org.example.jwt_security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserRepository repo;

    @Autowired
    private PasswordEncoder encoder;

    public String register(String username, String password, String role) {

        User user = new User();
        user.setUsername(username);
        user.setPassword(encoder.encode(password));
        user.setRole(role.toUpperCase());

        repo.save(user);

        return "User Registered Successfully";
    }

    public String login(String username, String password) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );

        if (authentication.isAuthenticated()) {

            User user = repo.findByUsername(username)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            return jwtService.generateToken(user.getUsername(), user.getRole());

        } else {
            throw new RuntimeException("Invalid credentials");
        }
    }
}