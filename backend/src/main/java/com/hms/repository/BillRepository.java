package com.hms.repository;

import com.hms.model.Bill;
import com.hms.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {
    List<Bill> findByPatient(Patient patient);
    List<Bill> findByPaymentStatus(Bill.PaymentStatus status);
}
