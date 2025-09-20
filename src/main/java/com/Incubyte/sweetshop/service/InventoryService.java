package com.Incubyte.sweetshop.service;

import com.Incubyte.sweetshop.dto.SweetDto;

public interface InventoryService {
    SweetDto purchaseSweet(Long sweetId, int quantity);

    SweetDto restockSweet(Long sweetId, int quantity);
}
