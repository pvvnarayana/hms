package com.hms.service;

import com.hms.model.LabTest;
import com.hms.repository.LabTestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LabTestService {

    @Autowired
    private LabTestRepository labTestRepository;

    public LabTest createLabTest(LabTest labTest) {
        return labTestRepository.save(labTest);
    }

    public LabTest updateLabTest(Long id, LabTest labTestDetails) {
        LabTest labTest = labTestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lab test not found"));

        labTest.setTestName(labTestDetails.getTestName());
        labTest.setTestDescription(labTestDetails.getTestDescription());
        labTest.setTechnician(labTestDetails.getTechnician());
        labTest.setResults(labTestDetails.getResults());
        labTest.setNotes(labTestDetails.getNotes());
        labTest.setStatus(labTestDetails.getStatus());
        
        if (labTestDetails.getStatus() == LabTest.Status.COMPLETED && labTest.getCompletedDate() == null) {
            labTest.setCompletedDate(LocalDateTime.now());
        }

        return labTestRepository.save(labTest);
    }

    public void deleteLabTest(Long id) {
        labTestRepository.deleteById(id);
    }

    public LabTest getLabTestById(Long id) {
        return labTestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lab test not found"));
    }

    public List<LabTest> getAllLabTests() {
        return labTestRepository.findAll();
    }

    public List<LabTest> getLabTestsByStatus(LabTest.Status status) {
        return labTestRepository.findByStatus(status);
    }

    public List<LabTest> getLabTestsByPatient(Long patientId) {
        return labTestRepository.findAll().stream()
                .filter(test -> test.getPatient().getId().equals(patientId))
                .toList();
    }
}
