package com.realestate.controller;

import com.realestate.entity.User;
import com.realestate.repository.UserRepository;
import com.realestate.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired UserRepository userRepository;
    @Autowired PasswordEncoder passwordEncoder;
    @Autowired JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Map<String, String> body) {
        String username = body.get("username");
        String password = body.get("password");
        String role = body.getOrDefault("role", "BUYER").toUpperCase();
        if(role.isEmpty()) role = "BUYER";

        if(username == null || password == null || username.trim().isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("message", "Username and password required"));
        }

        if(userRepository.findByUsername(username).isPresent()) {
            return ResponseEntity.badRequest().body(Map.of("message", "Username already exists - Please Login directly"));
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(role);
        userRepository.save(user);

        return ResponseEntity.ok(Map.of("message", "Registered successfully"));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> body) {
        String username = body.get("username");
        String password = body.get("password");

        Optional<User> opt = userRepository.findByUsername(username);
        if(opt.isEmpty()) {
            return ResponseEntity.status(401).body(Map.of("message", "User not found"));
        }

        User user = opt.get();
        if(!passwordEncoder.matches(password, user.getPassword())) {
            return ResponseEntity.status(401).body(Map.of("message", "Wrong password - Use admin/admin123 or owner/owner123"));
        }

        String token = jwtUtil.generateToken(user.getUsername(), user.getRole());

        Map<String, Object> res = new HashMap<>();
        res.put("token", token);
        res.put("username", user.getUsername());
        res.put("role", user.getRole());
        res.put("id", user.getId());
        res.put("message", "Login successful");

        return ResponseEntity.ok(res);
    }
}
