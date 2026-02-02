# Hospital Management System - API Documentation

Base URL: `http://localhost:8080/api`

## Authentication

All endpoints except `/auth/login` and `/auth/register` require authentication via JWT token in the Authorization header:

```
Authorization: Bearer <your_jwt_token>
```

### Auth Endpoints

#### 1. Login
- **URL**: `/auth/login`
- **Method**: `POST`
- **Auth Required**: No
- **Request Body**:
```json
{
  "username": "string",
  "password": "string"
}
```
- **Success Response**: `200 OK`
```json
{
  "token": "jwt_token_here",
  "type": "Bearer",
  "id": 1,
  "username": "admin",
  "fullName": "System Administrator",
  "email": "admin@hospital.com",
  "role": "ADMIN"
}
```

#### 2. Register
- **URL**: `/auth/register`
- **Method**: `POST`
- **Auth Required**: No
- **Request Body**:
```json
{
  "username": "string",
  "password": "string",
  "fullName": "string",
  "email": "string",
  "phone": "string",
  "role": "PATIENT|DOCTOR|NURSE|PHARMACIST|LAB_TECHNICIAN|RECEPTIONIST|ADMIN"
}
```
- **Success Response**: `200 OK` (Same as login response)

## Patient Management

#### List All Patients
- **URL**: `/patients`
- **Method**: `GET`
- **Auth Required**: Yes
- **Success Response**: `200 OK`
```json
[
  {
    "id": 1,
    "fullName": "John Doe",
    "dateOfBirth": "1990-01-01",
    "gender": "MALE",
    "phone": "1234567890",
    "email": "john@example.com",
    "address": "123 Main St",
    "bloodGroup": "O+",
    "medicalHistory": "No known allergies",
    "createdAt": "2024-01-01T00:00:00"
  }
]
```

#### Get Patient by ID
- **URL**: `/patients/{id}`
- **Method**: `GET`
- **Auth Required**: Yes

#### Search Patients
- **URL**: `/patients/search?name={name}`
- **Method**: `GET`
- **Auth Required**: Yes

#### Create Patient
- **URL**: `/patients`
- **Method**: `POST`
- **Auth Required**: Yes (ADMIN, DOCTOR, NURSE, RECEPTIONIST)
- **Request Body**:
```json
{
  "fullName": "string",
  "dateOfBirth": "YYYY-MM-DD",
  "gender": "MALE|FEMALE|OTHER",
  "phone": "string",
  "email": "string",
  "address": "string",
  "bloodGroup": "string",
  "medicalHistory": "string"
}
```

#### Update Patient
- **URL**: `/patients/{id}`
- **Method**: `PUT`
- **Auth Required**: Yes (ADMIN, DOCTOR, NURSE, RECEPTIONIST)
- **Request Body**: Same as Create Patient

#### Delete Patient
- **URL**: `/patients/{id}`
- **Method**: `DELETE`
- **Auth Required**: Yes (ADMIN only)

## Outpatient Management

#### List All Visits
- **URL**: `/outpatient`
- **Method**: `GET`
- **Auth Required**: Yes

#### Get Visit by ID
- **URL**: `/outpatient/{id}`
- **Method**: `GET`
- **Auth Required**: Yes

#### Get Visits by Patient
- **URL**: `/outpatient/patient/{patientId}`
- **Method**: `GET`
- **Auth Required**: Yes

#### Get Visits by Doctor
- **URL**: `/outpatient/doctor/{doctorId}`
- **Method**: `GET`
- **Auth Required**: Yes

#### Get Visits by Status
- **URL**: `/outpatient/status/{status}`
- **Method**: `GET`
- **Auth Required**: Yes
- **Status Values**: `SCHEDULED`, `IN_PROGRESS`, `COMPLETED`, `CANCELLED`

#### Create Visit
- **URL**: `/outpatient`
- **Method**: `POST`
- **Auth Required**: Yes (ADMIN, DOCTOR, NURSE, RECEPTIONIST)
- **Request Body**:
```json
{
  "patient": { "id": 1 },
  "doctor": { "id": 2 },
  "visitDate": "2024-01-01T10:00:00",
  "symptoms": "string",
  "diagnosis": "string",
  "prescription": "string",
  "notes": "string",
  "status": "SCHEDULED"
}
```

## Inpatient Management

#### List All Admissions
- **URL**: `/inpatient`
- **Method**: `GET`
- **Auth Required**: Yes

#### Get Active Admissions
- **URL**: `/inpatient/active`
- **Method**: `GET`
- **Auth Required**: Yes

#### Create Admission
- **URL**: `/inpatient`
- **Method**: `POST`
- **Auth Required**: Yes (ADMIN, DOCTOR, NURSE)
- **Request Body**:
```json
{
  "patient": { "id": 1 },
  "room": { "id": 1 },
  "doctor": { "id": 2 },
  "admissionDate": "2024-01-01T10:00:00",
  "reasonForAdmission": "string",
  "treatmentPlan": "string",
  "status": "ADMITTED"
}
```

#### Discharge Patient
- **URL**: `/inpatient/{id}/discharge`
- **Method**: `POST`
- **Auth Required**: Yes (ADMIN, DOCTOR)
- **Request Body**: `"Discharge summary text"`

## Room Management

#### List All Rooms
- **URL**: `/rooms`
- **Method**: `GET`
- **Auth Required**: Yes

#### Get Available Rooms
- **URL**: `/rooms/available`
- **Method**: `GET`
- **Auth Required**: Yes

#### Get Rooms by Type
- **URL**: `/rooms/type/{type}`
- **Method**: `GET`
- **Auth Required**: Yes
- **Room Types**: `GENERAL`, `SEMI_PRIVATE`, `PRIVATE`, `ICU`, `EMERGENCY`

#### Create Room
- **URL**: `/rooms`
- **Method**: `POST`
- **Auth Required**: Yes (ADMIN only)
- **Request Body**:
```json
{
  "roomNumber": "string",
  "roomType": "GENERAL|SEMI_PRIVATE|PRIVATE|ICU|EMERGENCY",
  "capacity": 1,
  "occupiedBeds": 0,
  "status": "AVAILABLE|OCCUPIED|MAINTENANCE|RESERVED",
  "pricePerDay": 100.00
}
```

## Pharmacy Management

#### List All Stock
- **URL**: `/pharmacy`
- **Method**: `GET`
- **Auth Required**: Yes

#### Get Low Stock Items
- **URL**: `/pharmacy/low-stock`
- **Method**: `GET`
- **Auth Required**: Yes

#### Create Stock Item
- **URL**: `/pharmacy`
- **Method**: `POST`
- **Auth Required**: Yes (ADMIN, PHARMACIST)
- **Request Body**:
```json
{
  "medicineName": "string",
  "batchNumber": "string",
  "quantity": 100,
  "reorderLevel": 50,
  "price": 10.00,
  "expiryDate": "2025-12-31T00:00:00",
  "manufacturer": "string"
}
```

## Lab Tests Management

#### List All Lab Tests
- **URL**: `/lab`
- **Method**: `GET`
- **Auth Required**: Yes

#### Get Lab Tests by Patient
- **URL**: `/lab/patient/{patientId}`
- **Method**: `GET`
- **Auth Required**: Yes

#### Get Lab Tests by Status
- **URL**: `/lab/status/{status}`
- **Method**: `GET`
- **Auth Required**: Yes
- **Status Values**: `PENDING`, `IN_PROGRESS`, `COMPLETED`, `CANCELLED`

#### Create Lab Test
- **URL**: `/lab`
- **Method**: `POST`
- **Auth Required**: Yes (ADMIN, DOCTOR)
- **Request Body**:
```json
{
  "patient": { "id": 1 },
  "doctor": { "id": 2 },
  "testName": "string",
  "testDescription": "string",
  "orderedDate": "2024-01-01T10:00:00",
  "status": "PENDING"
}
```

## Billing Management

#### List All Bills
- **URL**: `/billing`
- **Method**: `GET`
- **Auth Required**: Yes

#### Get Bills by Patient
- **URL**: `/billing/patient/{patientId}`
- **Method**: `GET`
- **Auth Required**: Yes

#### Get Pending Bills
- **URL**: `/billing/pending`
- **Method**: `GET`
- **Auth Required**: Yes

#### Create Bill
- **URL**: `/billing`
- **Method**: `POST`
- **Auth Required**: Yes (ADMIN, RECEPTIONIST)
- **Request Body**:
```json
{
  "patient": { "id": 1 },
  "admission": { "id": 1 },
  "roomCharges": 1000.00,
  "medicineCharges": 500.00,
  "labCharges": 300.00,
  "doctorCharges": 1000.00,
  "otherCharges": 200.00,
  "paidAmount": 0.00,
  "paymentStatus": "PENDING"
}
```

#### Make Payment
- **URL**: `/billing/{id}/payment`
- **Method**: `POST`
- **Auth Required**: Yes (ADMIN, RECEPTIONIST)
- **Request Body**:
```json
{
  "amount": 500.00
}
```

## User Management

#### List All Users
- **URL**: `/users`
- **Method**: `GET`
- **Auth Required**: Yes (ADMIN only)

#### Get Users by Role
- **URL**: `/users/role/{role}`
- **Method**: `GET`
- **Auth Required**: Yes (ADMIN, DOCTOR, NURSE)
- **Role Values**: `ADMIN`, `DOCTOR`, `NURSE`, `PHARMACIST`, `LAB_TECHNICIAN`, `RECEPTIONIST`, `PATIENT`

#### Update User
- **URL**: `/users/{id}`
- **Method**: `PUT`
- **Auth Required**: Yes (ADMIN only)

#### Delete User
- **URL**: `/users/{id}`
- **Method**: `DELETE`
- **Auth Required**: Yes (ADMIN only)

## Error Responses

All endpoints may return the following error responses:

- **401 Unauthorized**: Missing or invalid JWT token
- **403 Forbidden**: User doesn't have permission for this action
- **404 Not Found**: Resource not found
- **400 Bad Request**: Invalid request body or parameters
- **500 Internal Server Error**: Server error

Example error response:
```json
{
  "timestamp": "2024-01-01T00:00:00",
  "status": 401,
  "error": "Unauthorized",
  "message": "JWT token is missing or invalid",
  "path": "/api/patients"
}
```
