package com.Incubyte.sweetshop.controller;


import com.Incubyte.sweetshop.dto.AuthRequest;
import com.Incubyte.sweetshop.dto.AuthResponse;
import com.Incubyte.sweetshop.dto.RegisterRequest;
import com.Incubyte.sweetshop.model.User;
import com.Incubyte.sweetshop.service.AuthService;
import com.Incubyte.sweetshop.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest req) {
        User created = userService.registerUser(req.getUsername(), req.getPassword(), req.isAdmin());
        return ResponseEntity.ok().body("user_created:" + created.getUsername());
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthRequest request) {
        System.out.println("came to the authcontroler witj auth request"+request);
        AuthResponse resp = authService.login(request);
        return ResponseEntity.ok(resp);
    }
}