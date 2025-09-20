package com.Incubyte.sweetshop.util;

import com.Incubyte.sweetshop.dto.SweetDto;
import com.Incubyte.sweetshop.model.Sweet;

public class Mapper {
    public static SweetDto toDto(Sweet s) {
        if (s == null) return null;
        SweetDto dto = new SweetDto();
        dto.setId(s.getId());
        dto.setName(s.getName());
        dto.setCategory(s.getCategory());
        dto.setPrice(s.getPrice());
        dto.setQuantity(s.getQuantity());
        return dto;
    }

    public static Sweet toEntity(SweetDto dto) {
        if (dto == null) return null;
        return Sweet.builder()
                .id(dto.getId())
                .name(dto.getName())
                .category(dto.getCategory())
                .price(dto.getPrice())
                .quantity(dto.getQuantity())
                .build();
    }
}