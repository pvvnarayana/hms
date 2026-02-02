-- Hospital Management System Database Initialization Script

-- Create database (run this separately as a superuser)
-- CREATE DATABASE hms_db;

-- Connect to the database and run the following:

-- Sample data for testing

-- NOTE: For security reasons, sample users are not included by default.
-- Please register users through the application's registration endpoint at /api/auth/register
-- This ensures passwords are properly hashed using BCrypt.

-- Example: To create an admin user, use this API call:
-- POST http://localhost:8080/api/auth/register
-- {
--   "username": "admin",
--   "password": "admin123",
--   "fullName": "System Administrator",
--   "email": "admin@hospital.com",
--   "phone": "1234567890",
--   "role": "ADMIN"
-- }

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

-- Note: Always register users through the application to ensure proper password hashing and security.
