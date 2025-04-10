# Bank Transaction Microservice
This is a Spring Boot application for managing transactions, limits, and exchange rates. It provides REST APIs to create
transactions, set spending limits, retrieve exceeded transactions, and fetch exchange rates. The project uses PostgreSQL
as a database and is containerized using Docker and Docker Compose.

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








