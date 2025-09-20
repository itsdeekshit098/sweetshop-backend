package com.Incubyte.sweetshop.dto;

import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PurchaseRequest {
    @Min(1)
    private int quantity;
}