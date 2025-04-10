# Bank Transaction Microservice

This is a Spring Boot application for managing transactions, limits, and exchange rates. It provides REST APIs
to create transactions, set spending limits, retrieve exceeded transactions, and fetch exchange rates.
The project uses PostgreSQL as a database and is containerized using Docker and Docker Compose.

The application is deployed on a server and can be accessed from Belarus (access from outside can be limited) for
testing
at the URL: [link](http://85.209.148.18:8090/api/client/).

## Project Overview

### Features

- Create and manage transactions.
- Set and retrieve spending limits.
- Fetch exceeded transactions.
- Retrieve real-time exchange rates with scheduled updates.
- RESTful API with OpenAPI/Swagger documentation.

### Tech Stack

- Language: Java (using BigDecimal, enums, Stream API, etc.)
- Framework: Spring Boot (Web, Data JPA)
- Database: PostgreSQL
- ORM: Spring Data JPA with custom SQL queries (JOINs, subqueries, aggregations)
- Migration Tool: Liquibase
- HTTP Client: WebClient for external API calls
- DTO Mapping: MapStruct
- Documentation: Swagger (via springdoc-openapi)
- Build Tool: Maven
- Testing: JUnit, Mockito
- Other: Lombok, SLF4J for logging

### Task Analysis and Estimation

1. Task Analysis and Requirements Gathering: **2-3 hours**
2. Architecture and Data Model Design: **4-5 hours**
3. Project Setup (Spring Boot, Dependencies): **2 hours**
4. Implementation of Models, Repositories, and Migrations: **4-5 hours**
5. Creation of REST API (Controllers): **4-5 hours**
6. Integration with External API (Exchange Rates): **4-5 hours**
7. Development of Business Logic (Services): **8-10 hours**
8. Writing Tests (Unit + Integration): **6-8 hours**
9. Documentation (Swagger, README): **2-3 hours**
10. Additional Tasks (Docker): 4-5 **hours (optional)**

*Total: 40-50 hours*

### Getting Started

Follow these steps to set up and run the project using Docker.

**1. Clone the Repository**  
Clone the project from your Git repository:

   ```bash
   git clone https://github.com/dmitryshundrik/transaction-service.git
   cd transaction-service
   ```

**2. Configure Environment Variables**  
The application uses environment variables defined in docker-compose.yml. You need to adjust them:  
Exchange Rate API Key:
- Replace `API_KEY=api-key` in `docker-compose.yml` with your actual API key.  

Database Credentials (optional):  
- By default, the database uses `postgres:password`. To change, update `POSTGRES_USER`, `POSTGRES_PASSWORD`,
  and `SPRING_DATASOURCE_*` variables in `docker-compose.yml`.

**3. Build and Run with Docker Compose**  
Run the following command to build the application image and start both the app and database containers:

   ```bash
   docker-compose up --build 
   ```  

- The application will be available on http://localhost:8090.
- The PostgreSQL database will be accessible on localhost:5434 (mapped from container port 5432).

**4. Access the API**  
Once the containers are running, you can interact with the REST APIs using tools like Postman, cURL, or a browser.

- Create a Transaction:

   ```bash
   POST http://localhost:8090/api/transactions
   ```

- Get Exceeded Transactions:

   ```bash
   GET http://localhost:8090/api/client/exceeded-transactions
   ```

- Create a Limit:

   ```bash
   POST http://localhost:8090/api/client/limits
   ```

- Get All Limits:

   ```bash
   GET http://localhost:8090/api/client/limits
   ```

**5. API Documentation**  
Access the Swagger UI for interactive API documentation:

   ```bash
   http://localhost:8090/swagger-ui/index.html
   ```
   












