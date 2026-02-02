package com.hms.repository;

import com.hms.model.InpatientAdmission;
import com.hms.model.Patient;
import com.hms.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InpatientAdmissionRepository extends JpaRepository<InpatientAdmission, Long> {
    List<InpatientAdmission> findByPatient(Patient patient);
    List<InpatientAdmission> findByRoom(Room room);
    List<InpatientAdmission> findByStatus(InpatientAdmission.Status status);
}
