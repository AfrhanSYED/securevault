package com.securevault.service;

import com.securevault.dto.AuthRequest;
import com.securevault.dto.AuthResponse;
import com.securevault.entity.User;
import com.securevault.repository.UserRepository;
import com.securevault.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;

@Service
public class AuthService {

    @Autowired private UserRepository userRepo;
    @Autowired private JwtUtil jwtUtil;

    private final Pbkdf2PasswordEncoder encoder = new Pbkdf2PasswordEncoder("", 32, 65536, 256);

    public User register(AuthRequest req) {
        String salt = generateSalt();
        String encoded = encoder.encode(req.getPassword() + salt);

        User user = new User();
        user.setUsername(req.getUsername());
        user.setPassword(encoded);
        user.setSalt(salt);
        return userRepo.save(user);
    }

    public AuthResponse login(AuthRequest req) {
        User user = userRepo.findByUsername(req.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!encoder.matches(req.getPassword() + user.getSalt(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        String token = jwtUtil.generateToken(user.getUsername());
        return new AuthResponse(token, user.getUsername());
    }

    private String generateSalt() {
        byte[] salt = new byte[32];
        new SecureRandom().nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }
}