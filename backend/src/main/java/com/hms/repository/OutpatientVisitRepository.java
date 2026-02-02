package com.hms.repository;

import com.hms.model.OutpatientVisit;
import com.hms.model.Patient;
import com.hms.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OutpatientVisitRepository extends JpaRepository<OutpatientVisit, Long> {
    List<OutpatientVisit> findByPatient(Patient patient);
    List<OutpatientVisit> findByDoctor(User doctor);
    List<OutpatientVisit> findByStatus(OutpatientVisit.Status status);
    List<OutpatientVisit> findByVisitDateBetween(LocalDateTime start, LocalDateTime end);
}
