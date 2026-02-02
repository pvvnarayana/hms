package com.hms.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "inpatient_admissions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InpatientAdmission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;

    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    private User doctor;

    @Column(name = "admission_date", nullable = false)
    private LocalDateTime admissionDate = LocalDateTime.now();

    @Column(name = "discharge_date")
    private LocalDateTime dischargeDate;

    @Column(columnDefinition = "TEXT")
    private String reasonForAdmission;

    @Column(columnDefinition = "TEXT")
    private String treatmentPlan;

    @Column(columnDefinition = "TEXT")
    private String dischargeSummary;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status = Status.ADMITTED;

    public enum Status {
        ADMITTED, DISCHARGED, TRANSFERRED
    }
}
