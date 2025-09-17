package com.Incubyte.sweetshop.repository;

import com.Incubyte.sweetshop.model.Role;
import com.Incubyte.sweetshop.model.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("Should save and find user by username")
    void testFindByUsername() {

        User user = User.builder()
                .username("alice")
                .password("hashed-password")
                .roles(Set.of(Role.ROLE_USER))
                .build();
        userRepository.save(user);


        Optional<User> found = userRepository.findByUsername("alice");


        assertThat(found).isPresent();
        assertThat(found.get().getUsername()).isEqualTo("alice");
        assertThat(found.get().getRoles()).contains(Role.ROLE_USER);
    }

    @Test
    @DisplayName("Should check if username exists")
    void testExistsByUsername() {

        User user = User.builder()
                .username("bob")
                .password("hashed-password")
                .roles(Set.of(Role.ROLE_ADMIN))
                .build();
        userRepository.save(user);

        assertThat(userRepository.existsByUsername("bob")).isTrue();
        assertThat(userRepository.existsByUsername("nonexistent")).isFalse();
    }
}
