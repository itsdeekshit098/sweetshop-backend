package com.Incubyte.sweetshop.dto;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SweetDto {
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String category;

    @Min(0)
    private double price;

    @Min(0)
    private int quantity;
}