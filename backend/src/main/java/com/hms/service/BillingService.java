package com.hms.service;

import com.hms.model.Bill;
import com.hms.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BillingService {

    @Autowired
    private BillRepository billRepository;

    public Bill createBill(Bill bill) {
        bill.setUpdatedAt(LocalDateTime.now());
        return billRepository.save(bill);
    }

    public Bill updateBill(Long id, Bill billDetails) {
        Bill bill = billRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bill not found"));

        bill.setRoomCharges(billDetails.getRoomCharges());
        bill.setMedicineCharges(billDetails.getMedicineCharges());
        bill.setLabCharges(billDetails.getLabCharges());
        bill.setDoctorCharges(billDetails.getDoctorCharges());
        bill.setOtherCharges(billDetails.getOtherCharges());
        bill.setPaidAmount(billDetails.getPaidAmount());
        bill.setUpdatedAt(LocalDateTime.now());

        return billRepository.save(bill);
    }

    public Bill makePayment(Long billId, Double amount) {
        Bill bill = billRepository.findById(billId)
                .orElseThrow(() -> new RuntimeException("Bill not found"));

        bill.setPaidAmount(bill.getPaidAmount() + amount);
        bill.setUpdatedAt(LocalDateTime.now());

        return billRepository.save(bill);
    }

    public void deleteBill(Long id) {
        billRepository.deleteById(id);
    }

    public Bill getBillById(Long id) {
        return billRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bill not found"));
    }

    public List<Bill> getAllBills() {
        return billRepository.findAll();
    }

    public List<Bill> getBillsByPatient(Long patientId) {
        return billRepository.findAll().stream()
                .filter(bill -> bill.getPatient().getId().equals(patientId))
                .toList();
    }

    public List<Bill> getPendingBills() {
        return billRepository.findByPaymentStatus(Bill.PaymentStatus.PENDING);
    }
}
