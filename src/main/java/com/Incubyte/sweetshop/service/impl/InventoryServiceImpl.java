package com.Incubyte.sweetshop.service.impl;

import com.Incubyte.sweetshop.dto.SweetDto;
import com.Incubyte.sweetshop.exception.ResourceNotFoundException;
import com.Incubyte.sweetshop.model.Sweet;
import com.Incubyte.sweetshop.repository.SweetRepository;
import com.Incubyte.sweetshop.service.InventoryService;
import com.Incubyte.sweetshop.util.Mapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class InventoryServiceImpl implements InventoryService {
    private final SweetRepository sweetRepository;

    @Override
    public SweetDto purchaseSweet(Long sweetId, int quantity) {
        Sweet sweet = sweetRepository.findById(sweetId)
                .orElseThrow(() -> new ResourceNotFoundException("Sweet not found"));

        if (sweet.getQuantity() < quantity) {
            throw new IllegalArgumentException("Not enough stock available");
        }

        sweet.setQuantity(sweet.getQuantity() - quantity);
        Sweet updated = sweetRepository.save(sweet);
        return Mapper.toDto(updated);
    }

    @Override
    public SweetDto restockSweet(Long sweetId, int quantity) {
        Sweet sweet = sweetRepository.findById(sweetId)
                .orElseThrow(() -> new ResourceNotFoundException("Sweet not found"));

        if (quantity <= 0) {
            throw new IllegalArgumentException("Restock quantity must be positive");
        }

        sweet.setQuantity(sweet.getQuantity() + quantity);
        Sweet updated = sweetRepository.save(sweet);
        return Mapper.toDto(updated);
    }
}
