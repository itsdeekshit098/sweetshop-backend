package com.Incubyte.sweetshop.controller;

import com.Incubyte.sweetshop.dto.PurchaseRequest;
import com.Incubyte.sweetshop.dto.SweetDto;
import com.Incubyte.sweetshop.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sweets")
@RequiredArgsConstructor
public class InventoryController {
    private final InventoryService inventoryService;

    @PostMapping("/{id}/purchase")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<SweetDto> purchaseSweet(
            @PathVariable Long id,
            @RequestBody PurchaseRequest request) {
        SweetDto updatedSweet =inventoryService.purchaseSweet(id, request.getQuantity());
        return ResponseEntity.ok(updatedSweet);
    }

    @PostMapping("/{id}/restock")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<SweetDto> restockSweet(
            @PathVariable Long id,
            @RequestBody PurchaseRequest request) {
        SweetDto updatedSweet = inventoryService.restockSweet(id, request.getQuantity());
        return ResponseEntity.ok(updatedSweet);
    }
}
