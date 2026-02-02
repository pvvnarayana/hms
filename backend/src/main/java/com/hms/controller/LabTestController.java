package com.hms.controller;

import com.hms.model.LabTest;
import com.hms.service.LabTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lab")
@CrossOrigin(origins = "http://localhost:3000")
public class LabTestController {

    @Autowired
    private LabTestService labTestService;

    @GetMapping
    public ResponseEntity<List<LabTest>> getAllLabTests() {
        return ResponseEntity.ok(labTestService.getAllLabTests());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LabTest> getLabTestById(@PathVariable Long id) {
        return ResponseEntity.ok(labTestService.getLabTestById(id));
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<LabTest>> getLabTestsByPatient(@PathVariable Long patientId) {
        return ResponseEntity.ok(labTestService.getLabTestsByPatient(patientId));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<LabTest>> getLabTestsByStatus(@PathVariable LabTest.Status status) {
        return ResponseEntity.ok(labTestService.getLabTestsByStatus(status));
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    public ResponseEntity<LabTest> createLabTest(@RequestBody LabTest labTest) {
        return ResponseEntity.ok(labTestService.createLabTest(labTest));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR', 'LAB_TECHNICIAN')")
    public ResponseEntity<LabTest> updateLabTest(@PathVariable Long id, @RequestBody LabTest labTest) {
        return ResponseEntity.ok(labTestService.updateLabTest(id, labTest));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteLabTest(@PathVariable Long id) {
        labTestService.deleteLabTest(id);
        return ResponseEntity.noContent().build();
    }
}
