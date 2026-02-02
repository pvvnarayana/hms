package com.hms.service;

import com.hms.model.OutpatientVisit;
import com.hms.model.Patient;
import com.hms.model.User;
import com.hms.repository.OutpatientVisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OutpatientService {

    @Autowired
    private OutpatientVisitRepository outpatientVisitRepository;

    @Autowired
    private PatientService patientService;

    @Autowired
    private UserService userService;

    public OutpatientVisit createVisit(OutpatientVisit visit) {
        return outpatientVisitRepository.save(visit);
    }

    public OutpatientVisit updateVisit(Long id, OutpatientVisit visitDetails) {
        OutpatientVisit visit = outpatientVisitRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Visit not found"));

        visit.setSymptoms(visitDetails.getSymptoms());
        visit.setDiagnosis(visitDetails.getDiagnosis());
        visit.setPrescription(visitDetails.getPrescription());
        visit.setNotes(visitDetails.getNotes());
        visit.setStatus(visitDetails.getStatus());

        return outpatientVisitRepository.save(visit);
    }

    public void deleteVisit(Long id) {
        outpatientVisitRepository.deleteById(id);
    }

    public OutpatientVisit getVisitById(Long id) {
        return outpatientVisitRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Visit not found"));
    }

    public List<OutpatientVisit> getAllVisits() {
        return outpatientVisitRepository.findAll();
    }

    public List<OutpatientVisit> getVisitsByPatient(Long patientId) {
        Patient patient = patientService.getPatientById(patientId);
        return outpatientVisitRepository.findByPatient(patient);
    }

    public List<OutpatientVisit> getVisitsByDoctor(Long doctorId) {
        User doctor = userService.getUserById(doctorId);
        return outpatientVisitRepository.findByDoctor(doctor);
    }

    public List<OutpatientVisit> getVisitsByStatus(OutpatientVisit.Status status) {
        return outpatientVisitRepository.findByStatus(status);
    }
}
