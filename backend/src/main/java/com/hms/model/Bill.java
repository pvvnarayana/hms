package com.hms.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "bills")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "admission_id")
    private InpatientAdmission admission;

    @Column(nullable = false)
    private Double roomCharges = 0.0;

    @Column(nullable = false)
    private Double medicineCharges = 0.0;

    @Column(nullable = false)
    private Double labCharges = 0.0;

    @Column(nullable = false)
    private Double doctorCharges = 0.0;

    @Column(nullable = false)
    private Double otherCharges = 0.0;

    @Column(nullable = false)
    private Double totalAmount = 0.0;

    @Column(nullable = false)
    private Double paidAmount = 0.0;

    @Column(nullable = false)
    private Double balanceAmount = 0.0;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentStatus paymentStatus = PaymentStatus.PENDING;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();

    public enum PaymentStatus {
        PENDING, PARTIALLY_PAID, PAID, CANCELLED
    }

    @PrePersist
    @PreUpdate
    public void calculateTotals() {
        this.totalAmount = roomCharges + medicineCharges + labCharges + doctorCharges + otherCharges;
        this.balanceAmount = totalAmount - paidAmount;
        
        if (balanceAmount <= 0) {
            this.paymentStatus = PaymentStatus.PAID;
        } else if (paidAmount > 0) {
            this.paymentStatus = PaymentStatus.PARTIALLY_PAID;
        }
    }
}
