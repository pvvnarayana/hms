package com.hms.repository;

import com.hms.model.PharmacyStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PharmacyStockRepository extends JpaRepository<PharmacyStock, Long> {
    Optional<PharmacyStock> findByMedicineName(String medicineName);
    List<PharmacyStock> findByQuantityLessThanEqual(Integer quantity);
}
