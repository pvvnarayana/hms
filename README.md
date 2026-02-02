# Hospital Management System

A comprehensive full-stack Hospital Management System built with Spring Boot backend and React frontend, featuring complete authentication and authorization.

## Features

### Authentication & Authorization
- JWT-based authentication
- Role-based access control (RBAC)
- Multiple user roles: Admin, Doctor, Nurse, Pharmacist, Lab Technician, Receptionist, Patient
- Secure password encryption with BCrypt

### Modules

1. **Patient Management**
   - Register and manage patient records
   - Store medical history, contact information
   - Search and filter patients

2. **Outpatient Management**
   - Schedule and manage outpatient visits
   - Track symptoms, diagnosis, and prescriptions
   - Doctor-patient visit history

3. **Inpatient Management**
   - Manage patient admissions and discharges
   - Track treatment plans
   - Room allocation integration

4. **Room Allocation**
   - Manage different room types (General, Private, ICU, Emergency)
   - Track room availability and occupancy
   - Automatic status updates on admission/discharge

5. **Pharmacy Stock Management**
   - Track medicine inventory
   - Automatic low-stock alerts via email
   - Expiry date tracking
   - Reorder level management

6. **Lab Tests Management**
   - Order and track lab tests
   - Record test results
   - Status tracking (Pending, In Progress, Completed)

7. **Billing System**
   - Generate patient bills
   - Track payments (Full, Partial, Pending)
   - Breakdown of charges (Room, Medicine, Lab, Doctor fees)

8. **User Management**
   - Admin panel for user management
   - Role assignment and permissions

## Technology Stack

### Backend
- Java 17
- Spring Boot 3.2.0
- Spring Security (JWT)
- Spring Data JPA
- PostgreSQL
- Maven
- Lombok

### Frontend
- React 18
- React Router v6
- Axios
- CSS3

## Prerequisites

- Java 17 or higher
- Node.js 14 or higher
- PostgreSQL 12 or higher
- Maven 3.6 or higher

## Security Configuration (IMPORTANT)

Before running the application, you MUST configure the following environment variables:

### Required Environment Variables

1. **JWT Secret** (CRITICAL):
   ```bash
   # Generate a secure random secret
   export JWT_SECRET=$(openssl rand -hex 32)
   ```

2. **Database Credentials**:
   ```bash
   export DB_USERNAME=your_postgres_username
   export DB_PASSWORD=your_postgres_password
   ```

See [ENVIRONMENT_CONFIG.md](ENVIRONMENT_CONFIG.md) for complete configuration guide.

### Security Notes

- **NEVER** commit the `.env` file to version control
- **ALWAYS** use environment variables for sensitive data
- **NEVER** use default secrets in production
- **ROTATE** secrets regularly (every 90 days recommended)
- Reference `.env.example` files for required variables

## Database Setup

1. Install PostgreSQL
2. Create a database:
```sql
CREATE DATABASE hms_db;
```

3. Update database credentials in `backend/src/main/resources/application.yml`:
```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/hms_db
    username: your_username
    password: your_password
```

## Installation & Running

### Backend

```bash
cd backend
mvn clean install
mvn spring-boot:run
```

The backend will start on `http://localhost:8080`

### Frontend

```bash
cd frontend
npm install
npm start
```

The frontend will start on `http://localhost:3000`

## Default User Credentials

The application does NOT include default users for security reasons. You must register users through the application:

1. Start the application
2. Navigate to the registration page
3. Register your first admin user
4. **Note**: In production, restrict role assignment to prevent unauthorized admin creation

For initial setup, you can register an admin user via API:

```bash
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "username": "admin",
    "password": "SecurePassword123!",
    "fullName": "System Administrator",
    "email": "admin@hospital.com",
    "phone": "1234567890",
    "role": "ADMIN"
  }'
```

**Security Warning**: In a production environment, you should:
- Implement admin approval workflow for privileged roles
- Restrict public registration to PATIENT role only
- Or disable public registration entirely and create users via secure admin panel

## Email Configuration (Optional)

To enable pharmacy low-stock email alerts, configure SMTP settings in `application.yml`:

```yaml
spring:
  mail:
    host: smtp.gmail.com
    port: 587
    username: your-email@gmail.com
    password: your-app-password
```

## API Documentation

### Authentication Endpoints
- `POST /api/auth/login` - User login
- `POST /api/auth/register` - User registration

### Module Endpoints
All endpoints require authentication via JWT token in the Authorization header:
```
Authorization: Bearer <token>
```

- `/api/patients` - Patient management
- `/api/outpatient` - Outpatient visits
- `/api/inpatient` - Inpatient admissions
- `/api/rooms` - Room management
- `/api/pharmacy` - Pharmacy stock
- `/api/lab` - Lab tests
- `/api/billing` - Billing and payments
- `/api/users` - User management

## Security Features

- Password encryption using BCrypt
- JWT token-based authentication
- Role-based access control
- CORS configuration for frontend
- Session stateless security

## Project Structure

```
hms/
├── backend/
│   ├── src/main/java/com/hms/
│   │   ├── config/          # Security and app configuration
│   │   ├── controller/      # REST controllers
│   │   ├── dto/            # Data transfer objects
│   │   ├── model/          # Entity models
│   │   ├── repository/     # JPA repositories
│   │   ├── security/       # JWT utilities and filters
│   │   └── service/        # Business logic
│   └── src/main/resources/
│       └── application.yml  # Application configuration
└── frontend/
    ├── public/
    └── src/
        ├── components/     # Reusable components
        ├── context/       # React context (Auth)
        ├── pages/         # Page components
        └── services/      # API services

```

## License

This project is for educational purposes.