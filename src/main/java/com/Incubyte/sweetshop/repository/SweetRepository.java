package com.Incubyte.sweetshop.repository;

import com.Incubyte.sweetshop.model.Sweet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SweetRepository extends JpaRepository<Sweet, Long> {
    List<Sweet> findByNameContainingIgnoreCase(String name);
    List<Sweet> findByCategoryIgnoreCase(String category);

    @Query("SELECT s FROM Sweet s WHERE s.price BETWEEN :min AND :max")
    List<Sweet> findByPriceBetween(@Param("min") double min, @Param("max") double max);

    boolean existsByNameIgnoreCase(String name);
}
