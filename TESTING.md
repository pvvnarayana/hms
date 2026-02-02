# Testing Guide - Hospital Management System

## Backend Testing

### 1. Unit Testing

The backend includes Spring Boot Test dependencies. To run tests:

```bash
cd backend
mvn test
```

### 2. Manual API Testing with curl

#### Test Authentication

**Register a new user:**
```bash
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "username": "testadmin",
    "password": "admin123",
    "fullName": "Test Administrator",
    "email": "test@hospital.com",
    "phone": "1234567890",
    "role": "ADMIN"
  }'
```

**Login:**
```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "username": "testadmin",
    "password": "admin123"
  }'
```

Save the token from the response for subsequent requests.

#### Test Patient Management

**Create a patient:**
```bash
TOKEN="your_jwt_token_here"

curl -X POST http://localhost:8080/api/patients \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer $TOKEN" \
  -d '{
    "fullName": "Jane Smith",
    "dateOfBirth": "1985-05-15",
    "gender": "FEMALE",
    "phone": "9876543210",
    "email": "jane@example.com",
    "address": "456 Oak Avenue",
    "bloodGroup": "A+",
    "medicalHistory": "Allergic to penicillin"
  }'
```

**Get all patients:**
```bash
curl -X GET http://localhost:8080/api/patients \
  -H "Authorization: Bearer $TOKEN"
```

#### Test Room Management

**Create a room:**
```bash
curl -X POST http://localhost:8080/api/rooms \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer $TOKEN" \
  -d '{
    "roomNumber": "501",
    "roomType": "PRIVATE",
    "capacity": 1,
    "occupiedBeds": 0,
    "status": "AVAILABLE",
    "pricePerDay": 500.00
  }'
```

**Get available rooms:**
```bash
curl -X GET http://localhost:8080/api/rooms/available \
  -H "Authorization: Bearer $TOKEN"
```

### 3. Testing with Postman

1. Import the API endpoints from `API_DOCUMENTATION.md`
2. Create a Postman collection
3. Set up environment variables:
   - `base_url`: http://localhost:8080/api
   - `token`: (will be set after login)
4. Create a login request and use a test script to save the token:

```javascript
// In Postman Tests tab for login request
var jsonData = JSON.parse(responseBody);
pm.environment.set("token", jsonData.token);
```

5. Use `{{token}}` in Authorization header for other requests

## Frontend Testing

### 1. Running the Frontend

```bash
cd frontend
npm start
```

### 2. Manual Testing Checklist

#### Authentication Flow
- [ ] Visit http://localhost:3000
- [ ] Register a new user with ADMIN role
- [ ] Logout and login again
- [ ] Verify token is stored in localStorage
- [ ] Try accessing protected routes without login
- [ ] Verify redirect to login page

#### Dashboard
- [ ] Verify all modules are visible based on user role
- [ ] Click on each module card
- [ ] Verify user info is displayed correctly
- [ ] Test logout functionality

#### Patient Module
- [ ] Create a new patient
- [ ] View patient list
- [ ] Search for a patient
- [ ] Edit patient details
- [ ] Verify form validation
- [ ] Test delete patient (admin only)

#### Other Modules
- [ ] Test each module is accessible
- [ ] Verify placeholder pages are displayed
- [ ] Check back to dashboard navigation

### 3. Browser Console Testing

Open browser DevTools (F12) and check:

- No JavaScript errors in Console tab
- API requests in Network tab
- Token in Application > Local Storage
- Proper CORS headers in responses

## Integration Testing

### Complete User Flow Test

1. **Setup:**
   - Start PostgreSQL
   - Start backend (mvn spring-boot:run)
   - Start frontend (npm start)

2. **Admin Flow:**
   - Register as ADMIN
   - Login
   - Create rooms
   - Create users (doctors, nurses, etc.)
   - View all users

3. **Doctor Flow:**
   - Login as doctor
   - Create patients
   - Create outpatient visits
   - Order lab tests
   - View patient history

4. **Pharmacist Flow:**
   - Login as pharmacist
   - Add medicine stock
   - View low stock items
   - Update stock quantities

5. **Nurse Flow:**
   - Login as nurse
   - Create inpatient admission
   - Allocate room
   - Update patient records
   - Discharge patient

6. **Billing Flow:**
   - Login as admin/receptionist
   - Create bill for patient
   - Add various charges
   - Make partial payment
   - Complete payment
   - Verify bill status changes

## Performance Testing

### Load Testing with Apache Bench

```bash
# Test login endpoint
ab -n 100 -c 10 -p login.json -T application/json \
  http://localhost:8080/api/auth/login

# Where login.json contains:
# {"username":"testadmin","password":"admin123"}
```

### Expected Performance
- Response time: < 200ms for most GET requests
- Response time: < 500ms for POST/PUT requests
- Concurrent users: 50+ without degradation

## Security Testing

### 1. JWT Token Testing

- [ ] Try accessing protected endpoints without token
- [ ] Try using expired token
- [ ] Try modifying token payload
- [ ] Verify token expiration (24 hours)

### 2. Role-Based Access Testing

- [ ] Login as PATIENT
- [ ] Try accessing admin-only endpoints
- [ ] Verify 403 Forbidden response
- [ ] Test each role's permissions

### 3. Input Validation Testing

- [ ] Try SQL injection in input fields
- [ ] Test XSS in text areas
- [ ] Test with invalid email formats
- [ ] Test with negative numbers for quantities
- [ ] Test with future dates where not allowed

## Troubleshooting Common Issues

### Backend Issues

**Port 8080 already in use:**
```bash
# Find process using port 8080
lsof -i :8080
# Kill the process
kill -9 <PID>
```

**Database connection error:**
- Check PostgreSQL is running
- Verify credentials in application.yml
- Check database exists: `psql -l`

**JWT token errors:**
- Verify JWT secret is set
- Check token expiration time
- Ensure proper Authorization header format

### Frontend Issues

**CORS errors:**
- Verify backend CORS configuration
- Check allowed origins in SecurityConfig.java
- Restart backend after changes

**API connection refused:**
- Ensure backend is running on port 8080
- Check API base URL in api.js
- Verify no firewall blocking

**Login not working:**
- Check network tab for response
- Verify credentials
- Check token is saved in localStorage

## Automated Testing Scripts

### Backend Health Check

```bash
#!/bin/bash
# health-check.sh

echo "Checking backend health..."
response=$(curl -s -o /dev/null -w "%{http_code}" http://localhost:8080/api/auth/login)

if [ $response -eq 405 ] || [ $response -eq 401 ]; then
    echo "✓ Backend is running"
    exit 0
else
    echo "✗ Backend is not responding correctly (HTTP $response)"
    exit 1
fi
```

### Full Stack Test

```bash
#!/bin/bash
# full-test.sh

echo "Starting Full Stack Test..."

# Test backend
echo "1. Testing backend..."
curl -f http://localhost:8080/api/auth/login > /dev/null 2>&1
if [ $? -eq 0 ]; then
    echo "   ✓ Backend OK"
else
    echo "   ✗ Backend Failed"
    exit 1
fi

# Test frontend
echo "2. Testing frontend..."
curl -f http://localhost:3000 > /dev/null 2>&1
if [ $? -eq 0 ]; then
    echo "   ✓ Frontend OK"
else
    echo "   ✗ Frontend Failed"
    exit 1
fi

echo "All tests passed!"
```

## Next Steps

After completing all tests:
1. Document any bugs found
2. Create test reports
3. Set up CI/CD pipeline
4. Prepare for deployment
