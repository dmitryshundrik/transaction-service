# Bank Transaction Microservice
This project is a prototype of a microservice developed as a test task for a Junior Java Developer position. The microservice processes bank transactions, manages spending limits, and integrates with an external API to fetch exchange rates.
## Project Overview

### Purpose
The goal is to create a microservice that:
- Handles bank transactions in multiple currencies (e.g., KZT, RUB) and stores them in a database.
- Maintains monthly spending limits in USD for two categories: products and services (default: 1000 USD).
- Fetches and caches exchange rates (e.g., KZT/USD, RUB/USD) from an external API.
- Flags transactions exceeding the monthly limit (limit_exceeded).
- Allows clients to set new limits and retrieve transactions that exceed limits.

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
