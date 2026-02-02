package com.hms.service;

import com.hms.model.PharmacyStock;
import com.hms.repository.PharmacyStockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PharmacyService {

    @Autowired
    private PharmacyStockRepository pharmacyStockRepository;

    @Autowired
    private JavaMailSender mailSender;

    public PharmacyStock createStock(PharmacyStock stock) {
        return pharmacyStockRepository.save(stock);
    }

    public PharmacyStock updateStock(Long id, PharmacyStock stockDetails) {
        PharmacyStock stock = pharmacyStockRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Stock not found"));

        stock.setMedicineName(stockDetails.getMedicineName());
        stock.setBatchNumber(stockDetails.getBatchNumber());
        stock.setQuantity(stockDetails.getQuantity());
        stock.setReorderLevel(stockDetails.getReorderLevel());
        stock.setPrice(stockDetails.getPrice());
        stock.setExpiryDate(stockDetails.getExpiryDate());
        stock.setManufacturer(stockDetails.getManufacturer());
        stock.setUpdatedAt(LocalDateTime.now());

        return pharmacyStockRepository.save(stock);
    }

    public void deleteStock(Long id) {
        pharmacyStockRepository.deleteById(id);
    }

    public PharmacyStock getStockById(Long id) {
        return pharmacyStockRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Stock not found"));
    }

    public List<PharmacyStock> getAllStock() {
        return pharmacyStockRepository.findAll();
    }

    public List<PharmacyStock> getLowStockItems() {
        return pharmacyStockRepository.findAll().stream()
                .filter(stock -> stock.getQuantity() <= stock.getReorderLevel())
                .toList();
    }

    @Scheduled(cron = "0 0 9 * * *") // Every day at 9 AM
    public void checkLowStockAndSendAlerts() {
        List<PharmacyStock> lowStockItems = getLowStockItems();
        
        if (!lowStockItems.isEmpty()) {
            StringBuilder message = new StringBuilder("Low Stock Alert:\n\n");
            for (PharmacyStock item : lowStockItems) {
                message.append(String.format("Medicine: %s, Current Quantity: %d, Reorder Level: %d\n",
                        item.getMedicineName(), item.getQuantity(), item.getReorderLevel()));
            }

            String recipientEmail = System.getenv("NOTIFICATION_EMAIL");
            if (recipientEmail == null || recipientEmail.isEmpty()) {
                recipientEmail = "admin@hospital.com";
            }
            sendEmail(recipientEmail, "Pharmacy Low Stock Alert", message.toString());
        }
    }

    private void sendEmail(String to, String subject, String text) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);
            mailSender.send(message);
        } catch (Exception e) {
            System.err.println("Failed to send email: " + e.getMessage());
        }
    }
}
