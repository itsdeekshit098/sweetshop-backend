package com.Incubyte.sweetshop.service;


import com.Incubyte.sweetshop.config.JwtProvider;
import com.Incubyte.sweetshop.dto.AuthRequest;
import com.Incubyte.sweetshop.dto.AuthResponse;
import com.Incubyte.sweetshop.model.User;
import com.Incubyte.sweetshop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
    private final UserRepository userRepository;

    public AuthResponse login(AuthRequest request) {
        try {
            var token = new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());
            authenticationManager.authenticate(token);
            User user = userRepository.findByUsername(request.getUsername()).orElseThrow();
            String jwt = jwtProvider.generateToken(user.getUsername(), user.getRoles());
            return new AuthResponse(jwt);
        } catch (AuthenticationException ex) {
            throw new IllegalArgumentException("Invalid username/password");
        }
    }
}