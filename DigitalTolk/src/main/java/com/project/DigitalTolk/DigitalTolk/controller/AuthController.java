package com.project.DigitalTolk.DigitalTolk.controller;


import com.project.DigitalTolk.DigitalTolk.dto.LoginRequest;
import com.project.DigitalTolk.DigitalTolk.dto.TokenResponse;
import com.project.DigitalTolk.DigitalTolk.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        if ("admin".equals(request.getUsername()) && "admin123".equals(request.getPassword())) {
            String token = jwtUtil.generateToken(request.getUsername());
            return ResponseEntity.ok(new TokenResponse(token));
        }
        return ResponseEntity.status(401).body("Invalid credentials");
    }
}
