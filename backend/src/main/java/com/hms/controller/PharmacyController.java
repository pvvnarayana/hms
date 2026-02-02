package com.hms.controller;

import com.hms.model.PharmacyStock;
import com.hms.service.PharmacyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pharmacy")
@CrossOrigin(origins = "http://localhost:3000")
public class PharmacyController {

    @Autowired
    private PharmacyService pharmacyService;

    @GetMapping
    public ResponseEntity<List<PharmacyStock>> getAllStock() {
        return ResponseEntity.ok(pharmacyService.getAllStock());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PharmacyStock> getStockById(@PathVariable Long id) {
        return ResponseEntity.ok(pharmacyService.getStockById(id));
    }

    @GetMapping("/low-stock")
    public ResponseEntity<List<PharmacyStock>> getLowStockItems() {
        return ResponseEntity.ok(pharmacyService.getLowStockItems());
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'PHARMACIST')")
    public ResponseEntity<PharmacyStock> createStock(@RequestBody PharmacyStock stock) {
        return ResponseEntity.ok(pharmacyService.createStock(stock));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'PHARMACIST')")
    public ResponseEntity<PharmacyStock> updateStock(@PathVariable Long id, @RequestBody PharmacyStock stock) {
        return ResponseEntity.ok(pharmacyService.updateStock(id, stock));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteStock(@PathVariable Long id) {
        pharmacyService.deleteStock(id);
        return ResponseEntity.noContent().build();
    }
}
