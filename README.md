# Course Platform API

A production ready backend API for an online course learning platform built with Spring Boot, JWT authentication, PostgreSQL and Swagger/OpenAPI.
The platform supports course browsing, enrollment, progress tracking, authentication and search with proper security and global exception handling.

#  Swagger UI - https://course-platform-api-production-f001.up.railway.app/swagger-ui.html

# Live Deployment - Base URL (Railway) - https://course-platform-api-production-f001.up.railway.app/

# OpenAPI Docs - https://course-platform-api-production-f001.up.railway.app/v3/api-docs

# Tech Stack

Java 17
Spring Boot 3.2.6
Spring Security (JWT)
Spring Data JPA
PostgreSQL
Hibernate
Swagger / OpenAPI (springdoc)
Maven
Railway (Deployment)

# Authentication & Authorization

Uses JWT (Bearer Token) authentication
Stateless session management
Secure endpoints protected via Spring Security filter chain
Swagger supports JWT authorization

# Test Credentials

Use these credentials directly in Swagger:

1) {
  "email": "student9876@example.com",
  "password": "securePassword1234567"
}


2) {
  "email": "student@example.com",
  "password": "securePassword123"
}

# How to Test APIs in Swagger (Step-by-Step)

1) Open Swagger UI -   /swagger-ui.html

2) Call Login API:    POST /api/auth/login

3) Copy the token from response

4) Click Authorize (icon)

5) Paste token as:   Bearer <JWT_TOKEN>

6) Now test protected APIs (enroll, progress etc.)

# Public APIs (No Authorization Required)
# Auth Controller

POST /api/auth/register

POST /api/auth/login           - Use { "email": "student9876@example.com", "password": "securePassword1234567"} as schema in request body in swagger

# Course Controller
GET /api/courses

GET /api/courses/{id}          - Use physics-101 as a id string(path) in swagger for easy search

# Search Controller
GET /api/search?q=keyword      - Use Kinematics , velocity or speed as a id string for easy search


# Protected APIs (JWT Required)
# Enrollment Controller

POST /api/courses/{courseId}/enroll    -  use physics-101 in courseId string(path) in swagger 

#  Progress Controller

POST /api/subtopics/{subtopicId}/complete -    Use velocity or speed as subtopicId string(path) in swagger 

GET  /api/subtopics/enrollments/{enrollmentId}/progress    - Use 1 as enrollmentId* integer($int64)(path) in swagger 

# Core Features

1) User registration & login

2) JWT-based authentication

3) Course listing & details

4) Course enrollment

5) Subtopic completion tracking

6) Progress calculation per enrollment

7) Keyword-based course & subtopic search

8) Swagger documentation

9) Global exception handling

10) Production deployment on Railway

# Global Exception Handling

The application includes centralized error handling via @RestControllerAdvice.

Handled scenarios:
Resource not found (404)
Validation errors (400)
Authentication / authorization errors (401 / 403)
Internal server errors (500)

Example Error Response
{
  "status": 404,
  "error": "NOT_FOUND",
  "message": "Course not found",
  "path": "/api/courses/invalid-id",
  "timestamp": "2026-02-02T20:18:54.646Z"
}

# Project Structure

src/main/java/com/courseplatform/api
│
├── auth
├── course
├── enrollment
├── progress
├── search
├── security
│   ├── JwtAuthenticationFilter
│   └── SecurityConfig
├── exception
│   ├── ErrorResponse
│   ├── GlobalExceptionHandler
│   └── ResourceNotFoundException
└── config
    └── OpenApiConfig


# Local Setup (Optional)
Prerequisites

Java 17
Maven
PostgreSQL

# Steps
git clone https://github.com/Shrinath-816/course-platform-api.git
cd course-platform-api
mvn clean package
mvn spring-boot:run

Swagger will be available at:

http://localhost:8080/swagger-ui.html

# Environment Variables (Production)

Set via Railway:

JWT_SECRET=your-very-long-secure-secret-key
PGHOST=...
PGPORT=...
PGDATABASE=...
PGUSER=...
PGPASSWORD=...
JAVA_OPTS=...
PORT is auto-managed by Railway (do not override).

# Notes for Evaluators

All APIs are documented in Swagger
JWT authorization is required only where logically needed
Database is live and seeded
No frontend required to test functionality

# Author

Shrinath Patil
Backend Developer | Java | Spring Boot
