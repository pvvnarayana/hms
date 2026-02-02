package com.hms.controller;

import com.hms.model.OutpatientVisit;
import com.hms.service.OutpatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/outpatient")
@CrossOrigin(origins = "http://localhost:3000")
public class OutpatientController {

    @Autowired
    private OutpatientService outpatientService;

    @GetMapping
    public ResponseEntity<List<OutpatientVisit>> getAllVisits() {
        return ResponseEntity.ok(outpatientService.getAllVisits());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OutpatientVisit> getVisitById(@PathVariable Long id) {
        return ResponseEntity.ok(outpatientService.getVisitById(id));
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<OutpatientVisit>> getVisitsByPatient(@PathVariable Long patientId) {
        return ResponseEntity.ok(outpatientService.getVisitsByPatient(patientId));
    }

    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<OutpatientVisit>> getVisitsByDoctor(@PathVariable Long doctorId) {
        return ResponseEntity.ok(outpatientService.getVisitsByDoctor(doctorId));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<OutpatientVisit>> getVisitsByStatus(@PathVariable OutpatientVisit.Status status) {
        return ResponseEntity.ok(outpatientService.getVisitsByStatus(status));
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR', 'NURSE', 'RECEPTIONIST')")
    public ResponseEntity<OutpatientVisit> createVisit(@RequestBody OutpatientVisit visit) {
        return ResponseEntity.ok(outpatientService.createVisit(visit));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR', 'NURSE')")
    public ResponseEntity<OutpatientVisit> updateVisit(@PathVariable Long id, @RequestBody OutpatientVisit visit) {
        return ResponseEntity.ok(outpatientService.updateVisit(id, visit));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteVisit(@PathVariable Long id) {
        outpatientService.deleteVisit(id);
        return ResponseEntity.noContent().build();
    }
}
