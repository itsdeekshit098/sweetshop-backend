package com.Incubyte.sweetshop.service.impl;


import com.Incubyte.sweetshop.dto.SweetDto;
import com.Incubyte.sweetshop.model.Sweet;
import com.Incubyte.sweetshop.exception.ResourceNotFoundException;
import com.Incubyte.sweetshop.repository.SweetRepository;
import com.Incubyte.sweetshop.service.SweetService;
import com.Incubyte.sweetshop.util.Mapper;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SweetServiceImpl implements SweetService {
    private final SweetRepository sweetRepository;

    @Override
    public SweetDto addSweet(SweetDto dto) {
        if (sweetRepository.existsByNameIgnoreCase(dto.getName())) {
            throw new IllegalArgumentException("Sweet with this name already exists");
        }
        Sweet saved = sweetRepository.save(Mapper.toEntity(dto));
        return Mapper.toDto(saved);
    }

    @Override
    public List<SweetDto> listAll() {
        return sweetRepository.findAll().stream().map(Mapper::toDto).collect(Collectors.toList());
    }

    @Override
    public SweetDto getById(Long id) {
        Sweet s = sweetRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sweet not found"));
        return Mapper.toDto(s);
    }

    @Override
    public SweetDto update(Long id, SweetDto dto) {
        Sweet s = sweetRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sweet not found"));
        s.setName(dto.getName());
        s.setCategory(dto.getCategory());
        s.setPrice(dto.getPrice());
        s.setQuantity(dto.getQuantity());
        Sweet updated = sweetRepository.save(s);
        return Mapper.toDto(updated);
    }

    @Override
    public void delete(Long id) {
        if (!sweetRepository.existsById(id)) {
            throw new ResourceNotFoundException("Sweet not found");
        }
        sweetRepository.deleteById(id);
    }

    @Override
    public List<SweetDto> searchByName(String name) {
        return sweetRepository.findByNameContainingIgnoreCase(name)
                .stream().map(Mapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<SweetDto> searchByCategory(String category) {
        return sweetRepository.findByCategoryIgnoreCase(category)
                .stream().map(Mapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<SweetDto> searchByPriceRange(double min, double max) {
        return sweetRepository.findByPriceBetween(min, max)
                .stream().map(Mapper::toDto).collect(Collectors.toList());
    }

    public List<SweetDto> filterSweets(String category, Double minPrice, Double maxPrice) {
        return sweetRepository.findAll().stream()
                .filter(s -> category == null || s.getCategory().equalsIgnoreCase(category))
                .filter(s -> minPrice == null || maxPrice == null || (s.getPrice() >= minPrice && s.getPrice() <= maxPrice))
                .map(Mapper::toDto) // ✅ use Mapper.toDto
                .collect(Collectors.toList());
    }
}
