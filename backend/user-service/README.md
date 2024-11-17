# Create README.md in user-service directory
cd C:\Users\skunduru5\Documents\banking-system\banking-system\backend\user-service

# Create README.md with content
@"
# User Service

## Overview
This service handles user management in the banking system.

## Technical Stack
- Java 17
- Spring Boot 3.2.11
- MySQL
- Spring Security

## Package Structure
com.banking.user/
├── controller/ # REST API endpoints
├── service/ # Business logic
├── repository/ # Data access
├── model/ # Entity classes
└── dto/ # Data Transfer Objects


## API Endpoints
- POST /api/users/register
- POST /api/users/login
- GET /api/users/{id}
- PUT /api/users/{id}

## Setup Instructions
1. Configure application.yml
2. Setup MySQL database
3. Run UserServiceApplication

## Documentation Guidelines
- Use JavaDoc for classes and methods
- Follow clean code principles
- Document all API endpoints
  "@ > README.md