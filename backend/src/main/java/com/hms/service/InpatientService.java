package com.hms.service;

import com.hms.model.InpatientAdmission;
import com.hms.model.Room;
import com.hms.repository.InpatientAdmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class InpatientService {

    @Autowired
    private InpatientAdmissionRepository inpatientAdmissionRepository;

    @Autowired
    private RoomService roomService;

    public InpatientAdmission createAdmission(InpatientAdmission admission) {
        Room room = admission.getRoom();
        room.setOccupiedBeds(room.getOccupiedBeds() + 1);
        if (room.getOccupiedBeds() >= room.getCapacity()) {
            room.setStatus(Room.RoomStatus.OCCUPIED);
        }
        roomService.updateRoom(room.getId(), room);

        return inpatientAdmissionRepository.save(admission);
    }

    public InpatientAdmission updateAdmission(Long id, InpatientAdmission admissionDetails) {
        InpatientAdmission admission = inpatientAdmissionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Admission not found"));

        admission.setReasonForAdmission(admissionDetails.getReasonForAdmission());
        admission.setTreatmentPlan(admissionDetails.getTreatmentPlan());
        admission.setDischargeSummary(admissionDetails.getDischargeSummary());
        admission.setStatus(admissionDetails.getStatus());
        admission.setDischargeDate(admissionDetails.getDischargeDate());

        return inpatientAdmissionRepository.save(admission);
    }

    public InpatientAdmission dischargePatient(Long id, String dischargeSummary) {
        InpatientAdmission admission = inpatientAdmissionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Admission not found"));

        admission.setStatus(InpatientAdmission.Status.DISCHARGED);
        admission.setDischargeDate(LocalDateTime.now());
        admission.setDischargeSummary(dischargeSummary);

        Room room = admission.getRoom();
        room.setOccupiedBeds(room.getOccupiedBeds() - 1);
        if (room.getOccupiedBeds() < room.getCapacity()) {
            room.setStatus(Room.RoomStatus.AVAILABLE);
        }
        roomService.updateRoom(room.getId(), room);

        return inpatientAdmissionRepository.save(admission);
    }

    public void deleteAdmission(Long id) {
        inpatientAdmissionRepository.deleteById(id);
    }

    public InpatientAdmission getAdmissionById(Long id) {
        return inpatientAdmissionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Admission not found"));
    }

    public List<InpatientAdmission> getAllAdmissions() {
        return inpatientAdmissionRepository.findAll();
    }

    public List<InpatientAdmission> getActiveAdmissions() {
        return inpatientAdmissionRepository.findByStatus(InpatientAdmission.Status.ADMITTED);
    }
}
