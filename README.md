Payment Management System API
A robust RESTful API for managing customers, their bills, and associated payments, built with Spring Boot.

This backend was developed as a learning project to master core concepts of Spring Boot, Spring Data JPA, and building real-world, transactional web services.

✨ Key Features
Customer Management: Full CRUD (Create, Read, Update, Delete) operations for customers.

Bill Management: Create new bills linked to customers with automatic status calculation (PAID, PARTIALLY_PAID, NOT PAID).

Payment Tracking: Record payments against specific bills, which automatically updates the bill's balance and status.

Combined Operations: A single, transactional endpoint to create a new customer and their first bill simultaneously, including the initial payment record.

Secure by Design: Utilizes DTOs to create a secure API contract, preventing infinite recursion loops and decoupling the API from the internal database structure.

Relational Data Model: A well-structured schema with clear relationships between Customers, Bills, and Payments.

🛠️ Technology Stack
Framework: Spring Boot 3.x

Language: Java 17+

Data Persistence: Spring Data JPA / Hibernate

Database: PostgreSQL

Build Tool: Maven

🚀 Getting Started
Prerequisites
JDK 17 or higher

Maven 3.6+

A running instance of PostgreSQL

Installation & Setup
Clone the repository:

git clone [https://github.com/monibee127/SpringBoot.git](https://github.com/monibee127/SpringBoot.git)
cd SpringBoot


Configure the database:
Open the src/main/resources/application.properties file and update the spring.datasource properties with your PostgreSQL database credentials:

spring.datasource.url=jdbc:postgresql://localhost:5432/your_db_name
spring.datasource.username=your_postgres_username
spring.datasource.password=your_postgres_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true


Run the application:
Use the Maven wrapper to build and run the project.

./mvnw spring-boot:run


The application will start on http://localhost:8080.

📖 API Endpoints Guide
Here is a complete list of the available API endpoints.

Customer Endpoints
1. Create a Customer with their First Bill
Method: POST

URL: /customers/add

Description: Creates a new customer, their first bill, and a payment record for the initial amount paid. This is a transactional operation.

Request Body:

{
    "customerName": "Arun Kumar",
    "phoneNumber": "9840012345",
    "billno": 4001,
    "totalAmount": 10000.0,
    "paidAmount": 2500.0,
    "initialPaymentMethod": "GPay"
}


2. Get All Customers
Method: GET

URL: /customers/all

Description: Retrieves a list of all customers with a summary of their bills.

3. Get a Specific Customer by ID
Method: GET

URL: /customers/{id}

Description: Retrieves the full details for a single customer, including their complete list of bills.

Bill Endpoints
1. Add a New Bill to an Existing Customer
Method: POST

URL: /bills/add

Description: Creates a new bill and links it to an existing customer.

Request Body:

{
    "billno": 4003,
    "totalAmount": 1200.0,
    "paidAmount": 1200.0,
    "customer": {
        "customerId": 10
    }
}


2. Get a Specific Bill by ID
Method: GET

URL: /bills/{id}

Description: Retrieves the full details for a single bill, including customer information and a list of all payments made against it.

Payment Endpoints
1. Add a Payment to an Existing Bill
Method: POST

URL: /payments/add

Description: Records a new payment against an existing bill and updates the bill's balance and status.

Request Body:

{
    "bill": {
        "billno": 4001
    },
    "paymentMethod": "UPI",
    "amount": 3000.0
}
