package com.hms.repository;

import com.hms.model.LabTest;
import com.hms.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LabTestRepository extends JpaRepository<LabTest, Long> {
    List<LabTest> findByPatient(Patient patient);
    List<LabTest> findByStatus(LabTest.Status status);
}
