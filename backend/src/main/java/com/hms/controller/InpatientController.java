package com.hms.controller;

import com.hms.model.InpatientAdmission;
import com.hms.service.InpatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inpatient")
@CrossOrigin(origins = "http://localhost:3000")
public class InpatientController {

    @Autowired
    private InpatientService inpatientService;

    @GetMapping
    public ResponseEntity<List<InpatientAdmission>> getAllAdmissions() {
        return ResponseEntity.ok(inpatientService.getAllAdmissions());
    }

    @GetMapping("/{id}")
    public ResponseEntity<InpatientAdmission> getAdmissionById(@PathVariable Long id) {
        return ResponseEntity.ok(inpatientService.getAdmissionById(id));
    }

    @GetMapping("/active")
    public ResponseEntity<List<InpatientAdmission>> getActiveAdmissions() {
        return ResponseEntity.ok(inpatientService.getActiveAdmissions());
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR', 'NURSE')")
    public ResponseEntity<InpatientAdmission> createAdmission(@RequestBody InpatientAdmission admission) {
        return ResponseEntity.ok(inpatientService.createAdmission(admission));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR', 'NURSE')")
    public ResponseEntity<InpatientAdmission> updateAdmission(@PathVariable Long id, @RequestBody InpatientAdmission admission) {
        return ResponseEntity.ok(inpatientService.updateAdmission(id, admission));
    }

    @PostMapping("/{id}/discharge")
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    public ResponseEntity<InpatientAdmission> dischargePatient(@PathVariable Long id, @RequestBody String dischargeSummary) {
        return ResponseEntity.ok(inpatientService.dischargePatient(id, dischargeSummary));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteAdmission(@PathVariable Long id) {
        inpatientService.deleteAdmission(id);
        return ResponseEntity.noContent().build();
    }
}
