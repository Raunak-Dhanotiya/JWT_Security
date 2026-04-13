package org.example.jwt_security.service;
import org.example.jwt_security.entity.User;
import org.example.jwt_security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class AuthService {

    @Autowired
    private UserRepository repo;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder encoder;

    public String register(String username, String password) {
        repo.save(new User(username, encoder.encode(password)));
        return "User Registered";
    }

    public String login(String username, String password) {
        var user = repo.findByUsername(username).orElseThrow();

        if (!encoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        return jwtService.generateToken(username);
    }
}