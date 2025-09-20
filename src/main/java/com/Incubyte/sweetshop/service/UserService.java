package com.Incubyte.sweetshop.service;


import com.Incubyte.sweetshop.model.Role;
import com.Incubyte.sweetshop.model.User;
import com.Incubyte.sweetshop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User registerUser(String username, String rawPassword, boolean admin) {
        if (userRepository.existsByUsername(username)) {
            throw new IllegalArgumentException("username already exists");
        }
        User user = User.builder()
                .username(username)
                .password(passwordEncoder.encode(rawPassword))
                .roles(admin ? Set.of(Role.ROLE_ADMIN, Role.ROLE_USER) : Set.of(Role.ROLE_USER))
                .build();
        return userRepository.save(user);
    }
}