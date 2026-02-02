# Environment Configuration Guide

This document explains how to properly configure the Hospital Management System for different environments (development, staging, production).

## Backend Environment Variables

### Required Variables

#### Database Configuration
```bash
export DB_USERNAME=your_postgres_username
export DB_PASSWORD=your_postgres_password
```

#### JWT Secret (REQUIRED for production)
```bash
# Generate a secure random secret (256-bit recommended)
export JWT_SECRET=$(openssl rand -hex 32)
```

#### Email Configuration (Optional - for pharmacy alerts)
```bash
export MAIL_USERNAME=your-email@gmail.com
export MAIL_PASSWORD=your-app-specific-password
```

#### Notification Email (Optional)
```bash
export NOTIFICATION_EMAIL=admin@yourhospital.com
```

### Setting Environment Variables

#### Linux/macOS

**Temporary (current session):**
```bash
export DB_USERNAME=postgres
export DB_PASSWORD=mysecurepassword
export JWT_SECRET=$(openssl rand -hex 32)
```

**Permanent (.bashrc or .zshrc):**
```bash
echo 'export DB_USERNAME=postgres' >> ~/.bashrc
echo 'export DB_PASSWORD=mysecurepassword' >> ~/.bashrc
echo 'export JWT_SECRET='$(openssl rand -hex 32) >> ~/.bashrc
source ~/.bashrc
```

**Using .env file (with Spring Boot):**

Create `backend/.env`:
```properties
DB_USERNAME=postgres
DB_PASSWORD=mysecurepassword
JWT_SECRET=your-long-random-secret-here
MAIL_USERNAME=your-email@gmail.com
MAIL_PASSWORD=your-app-password
NOTIFICATION_EMAIL=admin@hospital.com
```

Then use a library like `dotenv` or set them before running:
```bash
export $(cat .env | xargs) && mvn spring-boot:run
```

#### Windows

**Command Prompt:**
```cmd
set DB_USERNAME=postgres
set DB_PASSWORD=mysecurepassword
set JWT_SECRET=your-long-random-secret-here
```

**PowerShell:**
```powershell
$env:DB_USERNAME="postgres"
$env:DB_PASSWORD="mysecurepassword"
$env:JWT_SECRET="your-long-random-secret-here"
```

**Permanent (System Environment Variables):**
1. Right-click "This PC" → Properties
2. Click "Advanced system settings"
3. Click "Environment Variables"
4. Add new system variables

## Frontend Environment Variables

Create a `.env` file in the `frontend/` directory:

```bash
REACT_APP_API_BASE_URL=http://localhost:8080/api
```

### Environment-Specific Configuration

#### Development (.env.development)
```bash
REACT_APP_API_BASE_URL=http://localhost:8080/api
```

#### Production (.env.production)
```bash
REACT_APP_API_BASE_URL=https://api.yourhospital.com/api
```

#### Staging (.env.staging)
```bash
REACT_APP_API_BASE_URL=https://staging-api.yourhospital.com/api
```

## Security Best Practices

### 1. Never Commit Secrets

Add to `.gitignore`:
```
.env
.env.local
.env.*.local
*.env
application-prod.yml
```

### 2. Generate Strong JWT Secret

```bash
# Use OpenSSL (recommended)
openssl rand -hex 32

# Or use Node.js
node -e "console.log(require('crypto').randomBytes(32).toString('hex'))"

# Or use Python
python -c "import secrets; print(secrets.token_hex(32))"
```

### 3. Rotate Secrets Regularly

- Change JWT secret every 90 days
- Update database passwords quarterly
- Rotate API keys and tokens

### 4. Use Different Secrets Per Environment

Never use the same secrets across development, staging, and production.

## Docker Environment Variables

If using Docker, create a `docker-compose.yml`:

```yaml
version: '3.8'

services:
  backend:
    build: ./backend
    environment:
      - DB_USERNAME=${DB_USERNAME}
      - DB_PASSWORD=${DB_PASSWORD}
      - JWT_SECRET=${JWT_SECRET}
      - MAIL_USERNAME=${MAIL_USERNAME}
      - MAIL_PASSWORD=${MAIL_PASSWORD}
      - NOTIFICATION_EMAIL=${NOTIFICATION_EMAIL}
    env_file:
      - .env
    ports:
      - "8080:8080"
    depends_on:
      - db

  frontend:
    build: ./frontend
    environment:
      - REACT_APP_API_BASE_URL=${REACT_APP_API_BASE_URL}
    ports:
      - "3000:3000"

  db:
    image: postgres:14
    environment:
      - POSTGRES_DB=hms_db
      - POSTGRES_USER=${DB_USERNAME}
      - POSTGRES_PASSWORD=${DB_PASSWORD}
    volumes:
      - postgres_data:/var/lib/postgresql/data

volumes:
  postgres_data:
```

Then create `.env` file:
```bash
DB_USERNAME=postgres
DB_PASSWORD=securepassword123
JWT_SECRET=your-generated-secret-here
REACT_APP_API_BASE_URL=http://localhost:8080/api
MAIL_USERNAME=
MAIL_PASSWORD=
NOTIFICATION_EMAIL=admin@hospital.com
```

Run with:
```bash
docker-compose up
```

## Cloud Deployment

### AWS

Use AWS Secrets Manager or Parameter Store:

```bash
# Store secrets
aws secretsmanager create-secret \
    --name hms/jwt-secret \
    --secret-string "your-secret-here"

# Retrieve in application
aws secretsmanager get-secret-value \
    --secret-id hms/jwt-secret \
    --query SecretString \
    --output text
```

### Heroku

```bash
heroku config:set DB_USERNAME=postgres
heroku config:set DB_PASSWORD=yourpassword
heroku config:set JWT_SECRET=$(openssl rand -hex 32)
```

### Azure

Use Azure Key Vault:

```bash
az keyvault secret set \
    --vault-name myHMSVault \
    --name JWT-SECRET \
    --value "your-secret-here"
```

## Verification

### Verify Backend Configuration

```bash
# Check if environment variables are set
echo $DB_USERNAME
echo $JWT_SECRET

# Start backend and check logs
cd backend
mvn spring-boot:run

# Look for: "Started HospitalManagementSystemApplication"
```

### Verify Frontend Configuration

```bash
# Check environment
echo $REACT_APP_API_BASE_URL

# Start frontend
cd frontend
npm start

# Check browser console for API calls
```

## Troubleshooting

### JWT Secret Not Set

**Error:** "JWT secret is not configured"

**Solution:**
```bash
export JWT_SECRET=$(openssl rand -hex 32)
```

### Database Connection Failed

**Error:** "Connection refused" or "Authentication failed"

**Solution:**
1. Check PostgreSQL is running
2. Verify DB_USERNAME and DB_PASSWORD
3. Check `application.yml` datasource URL

### CORS Errors in Frontend

**Error:** "Access-Control-Allow-Origin"

**Solution:**
1. Verify REACT_APP_API_BASE_URL matches backend URL
2. Check SecurityConfig.java CORS configuration
3. Ensure backend is running

## Configuration Checklist

Before deploying to production:

- [ ] Set strong JWT_SECRET (not default)
- [ ] Configure DB_USERNAME and DB_PASSWORD
- [ ] Set NOTIFICATION_EMAIL for alerts
- [ ] Configure MAIL_USERNAME and MAIL_PASSWORD (if using email)
- [ ] Set REACT_APP_API_BASE_URL to production URL
- [ ] Remove default values from application.yml
- [ ] Add .env files to .gitignore
- [ ] Test all environment variables are loaded
- [ ] Verify secrets are not in version control
- [ ] Document all required environment variables
- [ ] Set up secret rotation schedule

## Additional Resources

- [Spring Boot External Configuration](https://docs.spring.io/spring-boot/docs/current/reference/html/features.html#features.external-config)
- [Create React App Environment Variables](https://create-react-app.dev/docs/adding-custom-environment-variables/)
- [12-Factor App Config](https://12factor.net/config)
