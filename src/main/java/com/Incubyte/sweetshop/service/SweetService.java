package com.Incubyte.sweetshop.service;


import com.Incubyte.sweetshop.dto.SweetDto;

import java.util.List;

public interface SweetService {
    List<SweetDto> filterSweets(String category, Double minPrice, Double maxPrice);
    SweetDto addSweet(SweetDto dto);

    List<SweetDto> listAll();

    SweetDto getById(Long id);

    SweetDto update(Long id, SweetDto dto);

    void delete(Long id);

    List<SweetDto> searchByName(String name);

    List<SweetDto> searchByCategory(String category);

    List<SweetDto> searchByPriceRange(double min, double max);

}