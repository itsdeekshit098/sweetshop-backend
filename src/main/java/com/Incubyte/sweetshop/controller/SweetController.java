package com.Incubyte.sweetshop.controller;

import com.Incubyte.sweetshop.dto.SweetDto;
import com.Incubyte.sweetshop.service.SweetService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sweets")
@RequiredArgsConstructor
public class SweetController {
    private final SweetService sweetService;

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<SweetDto> addSweet(@Valid @RequestBody SweetDto dto) {
        return ResponseEntity.ok(sweetService.addSweet(dto));
    }

    @GetMapping
    public ResponseEntity<List<SweetDto>> listAll() {
        return ResponseEntity.ok(sweetService.listAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SweetDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(sweetService.getById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<SweetDto>> search(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice
    ) {
        if (name != null) return ResponseEntity.ok(sweetService.searchByName(name));
        if (category != null) return ResponseEntity.ok(sweetService.searchByCategory(category));
        if (minPrice != null && maxPrice != null) return ResponseEntity.ok(sweetService.searchByPriceRange(minPrice, maxPrice));
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/filter")
    public ResponseEntity<List<SweetDto>> filter(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String price // receive as string "min-max"
    ) {
        Double minPrice = null;
        Double maxPrice = null;

        // Parse price string if provided
        if (price != null && price.contains("-")) {
            String[] parts = price.split("-");
            try {
                minPrice = Double.parseDouble(parts[0]);
                maxPrice = Double.parseDouble(parts[1]);
            } catch (NumberFormatException e) {
                return ResponseEntity.badRequest().build();
            }
        }

        System.out.println("Filter request: category=" + category + ", minPrice=" + minPrice + ", maxPrice=" + maxPrice);
        List<SweetDto> result = sweetService.filterSweets(category, minPrice, maxPrice);
        return ResponseEntity.ok(result);
    }


    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<SweetDto> update(@PathVariable Long id, @Valid @RequestBody SweetDto dto) {
        return ResponseEntity.ok(sweetService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        sweetService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
