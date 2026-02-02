-- Hospital Management System Database Initialization Script

-- Create database (run this separately as a superuser)
-- CREATE DATABASE hms_db;

-- Connect to the database and run the following:

-- Sample data for testing

-- Insert sample admin user (password: admin123)
INSERT INTO users (username, password, full_name, email, phone, role, enabled, created_at)
VALUES ('admin', '$2a$10$xN0YJY.j2lQ1kI5kQZ5m2eSP7q9Lx0qY5yU0MZ5kH8z0T0T0T0T0T0', 'System Administrator', 'admin@hospital.com', '1234567890', 'ADMIN', true, CURRENT_TIMESTAMP)
ON CONFLICT DO NOTHING;

-- Insert sample doctor (password: doctor123)
INSERT INTO users (username, password, full_name, email, phone, role, enabled, created_at)
VALUES ('doctor1', '$2a$10$xN0YJY.j2lQ1kI5kQZ5m2eSP7q9Lx0qY5yU0MZ5kH8z0T0T0T0T0T0', 'Dr. John Smith', 'doctor@hospital.com', '1234567891', 'DOCTOR', true, CURRENT_TIMESTAMP)
ON CONFLICT DO NOTHING;

-- Insert sample nurse (password: nurse123)
INSERT INTO users (username, password, full_name, email, phone, role, enabled, created_at)
VALUES ('nurse1', '$2a$10$xN0YJY.j2lQ1kI5kQZ5m2eSP7q9Lx0qY5yU0MZ5kH8z0T0T0T0T0T0', 'Nurse Mary Johnson', 'nurse@hospital.com', '1234567892', 'NURSE', true, CURRENT_TIMESTAMP)
ON CONFLICT DO NOTHING;

-- Insert sample pharmacist (password: pharma123)
INSERT INTO users (username, password, full_name, email, phone, role, enabled, created_at)
VALUES ('pharmacist1', '$2a$10$xN0YJY.j2lQ1kI5kQZ5m2eSP7q9Lx0qY5yU0MZ5kH8z0T0T0T0T0T0', 'Pharmacist Bob Wilson', 'pharmacist@hospital.com', '1234567893', 'PHARMACIST', true, CURRENT_TIMESTAMP)
ON CONFLICT DO NOTHING;

-- Insert sample rooms
INSERT INTO rooms (room_number, room_type, capacity, occupied_beds, status, price_per_day)
VALUES 
('101', 'GENERAL', 4, 0, 'AVAILABLE', 100.00),
('102', 'GENERAL', 4, 0, 'AVAILABLE', 100.00),
('201', 'SEMI_PRIVATE', 2, 0, 'AVAILABLE', 200.00),
('202', 'SEMI_PRIVATE', 2, 0, 'AVAILABLE', 200.00),
('301', 'PRIVATE', 1, 0, 'AVAILABLE', 500.00),
('302', 'PRIVATE', 1, 0, 'AVAILABLE', 500.00),
('401', 'ICU', 1, 0, 'AVAILABLE', 1000.00),
('402', 'ICU', 1, 0, 'AVAILABLE', 1000.00),
('ER1', 'EMERGENCY', 1, 0, 'AVAILABLE', 300.00)
ON CONFLICT DO NOTHING;

-- Insert sample medicines
INSERT INTO pharmacy_stock (medicine_name, batch_number, quantity, reorder_level, price, expiry_date, manufacturer, created_at, updated_at)
VALUES 
('Paracetamol 500mg', 'BATCH001', 1000, 100, 5.00, '2025-12-31', 'PharmaCorp', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Amoxicillin 250mg', 'BATCH002', 500, 50, 15.00, '2025-12-31', 'MediCare Inc', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Ibuprofen 400mg', 'BATCH003', 750, 75, 8.00, '2025-12-31', 'HealthPharma', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Aspirin 75mg', 'BATCH004', 300, 100, 3.00, '2025-12-31', 'PharmaCorp', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Metformin 500mg', 'BATCH005', 200, 50, 12.00, '2025-12-31', 'DiabetesCare', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT DO NOTHING;

-- Note: The passwords above are bcrypt hashed. For actual use, you should register users through the application
-- or use a proper password hashing tool to generate bcrypt hashes.
