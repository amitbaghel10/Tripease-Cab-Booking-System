# Tripease – Cab Booking Management System

A backend application built with Java and Spring Boot to manage customers, drivers, cabs, and cab bookings.

## Overview

Tripease provides REST APIs for managing customer and driver information, registering cabs, and booking available cabs. The booking workflow validates the customer, finds an available cab, calculates the fare based on trip distance and the cab's per-kilometer rate, and records booking details.

## Technologies Used

- Java
- Spring Boot
- Spring Data JPA
- Hibernate
- MySQL
- Maven
- Lombok
- Postman
- Spring Mail

## Features

- Customer registration and retrieval
- Driver registration and retrieval
- Cab registration and availability management
- Cab booking based on availability
- Fare calculation using distance and per-kilometer rate
- Booking status and timestamp management
- Custom exceptions for customer, driver, and cab availability errors
- REST API testing using Postman
- Email configuration using Spring Mail

## Project Structure

```text
src/main/java/com/example/tripease/
├── Controller/
├── DTO/
│   ├── Request/
│   └── Response/
├── Enum/
├── Exception/
├── Model/
├── Repository/
├── Service/
├── Transformer/
└── TripeaseApplication.java
```

- **Controller:** Handles incoming HTTP requests.
- **DTO:** Defines request and response objects.
- **Model:** Contains the application's entity classes.
- **Repository:** Provides database access through Spring Data JPA.
- **Service:** Contains business logic.
- **Transformer:** Converts between DTOs and entity objects.
- **Exception:** Defines custom application exceptions.
- **Enum:** Contains predefined values such as gender and trip status.

## Booking Workflow

1. A customer is registered in the system.
2. A driver is registered and a cab is associated with the driver.
3. The customer submits a cab booking request.
4. The booking service checks whether the customer exists.
5. The application searches for an available cab.
6. The fare is calculated using trip distance and the cab's per-kilometer rate.
7. The booking is saved, and the cab's availability is updated.
8. The application prepares a booking response with the relevant customer, cab, and driver information.

## Setup Instructions

### Prerequisites

- Java Development Kit compatible with the project configuration
- MySQL Server
- Git

### 1. Clone the repository

```bash
git clone https://github.com/amitbaghel10/Tripease-Cab-Booking-System.git
cd Tripease-Cab-Booking-System
```

### 2. Configure the database

Create a MySQL database named `tripease-db`.

Configure the database URL, username, and password in `src/main/resources/application.properties`. Use environment variables or another secure local configuration for credentials.

### 3. Configure email (if using email functionality)

Configure your SMTP username and password using environment variables or secure local configuration. Do not commit actual email credentials.

### 4. Run the application

On Windows:

```bash
mvnw.cmd spring-boot:run
```

Alternatively, run `TripeaseApplication.java` from your IDE.

## API Testing

Use Postman to test the application's REST endpoints.

Examples of available endpoint patterns include:

- `POST /customer/add`
- `GET /customer/get/customer-id/{id}`
- `POST /driver/add`
- `POST /cab/register/driver/{driverId}`
- `POST /booking/book/customer/{customerId}`

Check the controller classes for the complete routes and request formats.

## Learning Outcomes

This project provided practical experience with REST API development, layered architecture, Spring Data JPA, entity relationships, database operations, exception handling, and testing API workflows.

## Author

Amit Baghel

GitHub: https://github.com/amitbaghel10
