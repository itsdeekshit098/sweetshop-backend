package com.Incubyte.sweetshop.repository;

import com.Incubyte.sweetshop.model.Sweet;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // use real DB
class SweetRepositoryTest {

    @Autowired
    private SweetRepository sweetRepository;

    @Test
    @DisplayName("Should save and find sweets by name containing")
    void testFindByNameContainingIgnoreCase() {
        Sweet sweet = Sweet.builder()
                .name("Ladoo")
                .category("Indian")
                .price(10.0)
                .quantity(100)
                .build();
        sweetRepository.save(sweet);

        List<Sweet> results = sweetRepository.findByNameContainingIgnoreCase("ladoo");

        assertThat(results).isNotEmpty();
        assertThat(results.get(0).getName()).isEqualTo("Ladoo");
    }

    @Test
    @DisplayName("Should find sweets by category ignoring case")
    void testFindByCategoryIgnoreCase() {
        Sweet sweet = Sweet.builder()
                .name("Barfi")
                .category("Indian")
                .price(15.0)
                .quantity(50)
                .build();
        sweetRepository.save(sweet);

        List<Sweet> results = sweetRepository.findByCategoryIgnoreCase("indian");

        assertThat(results).isNotEmpty();
        assertThat(results.get(0).getCategory()).isEqualTo("Indian");
    }

    @Test
    @DisplayName("Should find sweets by price range")
    void testFindByPriceBetween() {
        Sweet sweet1 = Sweet.builder()
                .name("Gulab Jamun")
                .category("Indian")
                .price(20.0)
                .quantity(30)
                .build();
        Sweet sweet2 = Sweet.builder()
                .name("Kaju Katli")
                .category("Indian")
                .price(50.0)
                .quantity(40)
                .build();

        sweetRepository.save(sweet1);
        sweetRepository.save(sweet2);

        List<Sweet> results = sweetRepository.findByPriceBetween(15.0, 30.0);

        assertThat(results).extracting(Sweet::getName).contains("Gulab Jamun");
        assertThat(results).extracting(Sweet::getName).doesNotContain("Kaju Katli");
    }

    @Test
    @DisplayName("Should check if sweet exists by name ignoring case")
    void testExistsByNameIgnoreCase() {
        Sweet sweet = Sweet.builder()
                .name("Rasgulla")
                .category("Indian")
                .price(25.0)
                .quantity(60)
                .build();
        sweetRepository.save(sweet);

        assertThat(sweetRepository.existsByNameIgnoreCase("rasgulla")).isTrue();
        assertThat(sweetRepository.existsByNameIgnoreCase("jalebi")).isFalse();
    }
}
