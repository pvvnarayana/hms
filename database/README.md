# Database Setup Instructions

## Prerequisites
- PostgreSQL 12 or higher installed and running

## Step 1: Create Database

Connect to PostgreSQL as a superuser (e.g., postgres):

```bash
psql -U postgres
```

Then create the database:

```sql
CREATE DATABASE hms_db;
\q
```

## Step 2: Configure Application

Update the database credentials in `backend/src/main/resources/application.yml`:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/hms_db
    username: postgres  # Change to your PostgreSQL username
    password: postgres  # Change to your PostgreSQL password
```

## Step 3: Run Application

The application uses Spring Boot's `ddl-auto: update` setting, which will automatically create all necessary tables when you first run the application.

```bash
cd backend
mvn spring-boot:run
```

## Step 4: Initialize Sample Data (Optional)

After the application has created the tables, you can optionally run the initialization script to add sample data:

```bash
psql -U postgres -d hms_db -f database/init.sql
```

## Sample Users

The init.sql script creates the following sample users (all passwords need to be properly hashed):

- **Admin**: username: `admin`, password: `admin123`
- **Doctor**: username: `doctor1`, password: `doctor123`
- **Nurse**: username: `nurse1`, password: `nurse123`
- **Pharmacist**: username: `pharmacist1`, password: `pharma123`

**Note**: For production use, you should register users through the application's registration page, which will properly hash the passwords.

## Tables Created

The application will automatically create the following tables:

- `users` - System users with roles
- `patients` - Patient records
- `outpatient_visits` - Outpatient appointments
- `inpatient_admissions` - Inpatient admissions
- `rooms` - Hospital rooms
- `pharmacy_stock` - Pharmacy inventory
- `lab_tests` - Laboratory tests
- `bills` - Patient billing

## Troubleshooting

If you encounter connection issues:

1. Ensure PostgreSQL is running:
   ```bash
   sudo systemctl status postgresql  # Linux
   # or
   brew services list  # macOS
   ```

2. Check PostgreSQL is accepting connections on port 5432:
   ```bash
   netstat -an | grep 5432
   ```

3. Verify database credentials match those in `application.yml`

4. Check PostgreSQL logs for any errors:
   ```bash
   tail -f /var/log/postgresql/postgresql-*.log
   ```
