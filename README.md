# Bank Applications

A web application for bank employees to manage incoming credit applications, view application records, and calculate preliminary credit scores based on requested loan amounts.

## Features

- Create credit applications for existing clients.
- View all credit applications in a table.
- Calculate preliminary credit scores based on loan amounts.
- Update application data dynamically without reloading the page.
- Display application statuses and calculated scores.
- Handle application errors using a global exception handler.
- Store client and application data in PostgreSQL.
- Use both Spring Data JPA and raw JDBC for database operations.

## Technologies

- **Java 17**
- **Spring Boot 3.5.16**
- **Spring Web** — REST API
- **Spring Data JPA** — ORM and database operations
- **Spring JDBC** — raw SQL queries using `JdbcTemplate`
- **PostgreSQL 18.4** — relational database
- **Thymeleaf** — server-side HTML rendering
- **HTML5, CSS3, JavaScript** — frontend
- **MapStruct** — DTO mapping
- **Lombok** — boilerplate code reduction
- **Maven** — build and dependency management
- **Docker Compose** — database containerization

## Credit Score Calculation

The preliminary credit score is determined by the requested loan amount:

| Loan Amount | Credit Score |
|---|---:|
| Less than 100,000 | 10 |
| From 100,000 to 500,000 (inclusive) | 50 |
| More than 500,000 | 100 |

The score is preliminary and is calculated exclusively from the requested loan amount.

## Database Structure

The application uses two main entities.

### Client

| Field | Description |
|---|---|
| id | Unique client identifier |
| firstName | Client's first name |
| lastName | Client's last name |
| email | Client's email address |
| phone | Client's phone number |
| createdAt | Record creation timestamp |
| updatedAt | Last update timestamp |

### Application

| Field | Description |
|---|---|
| id | Unique application identifier |
| amount | Requested loan amount |
| score | Preliminary credit score |
| status | Application status |
| createdAt | Record creation timestamp |
| updatedAt | Last update timestamp |
| client | Associated client |

**Relationship:** Many applications can belong to one client (`@ManyToOne`).

A standalone SQL schema script is available at:

`src/main/resources/db/schema.sql`

## Project Structure

```text
src/
├── main/
│   ├── java/com/bank/applications/
│   │   ├── controller/
│   │   ├── service/
│   │   ├── repository/
│   │   ├── entity/
│   │   ├── dto/
│   │   ├── mapper/
│   │   └── exception/
│   │
│   └── resources/
│       ├── static/
│       │   ├── css/
│       │   └── js/
│       ├── templates/
│       ├── db/
│       │   └── schema.sql
│       └── application.properties/
│
├── pom.xml
├── docker-compose.yml
└── README.md
```

## REST API

Base URL:

`http://localhost:8081`

### Applications

| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/applications` | Create a credit application |
| GET | `/api/applications` | Retrieve all credit applications |
| POST | `/api/applications/{id}/calculate` | Calculate the credit score |

**Example request — create an application:**

```json
{
  "amount": 250000,
  "clientId": 1
}
```

**Example response:**

```json
{
  "id": 1,
  "amount": 250000.00,
  "score": null,
  "status": "NEW",
  "client": {
    "id": 1,
    "firstName": "Ivan",
    "lastName": "Petrov"
  }
}
```

The response contains additional client and timestamp fields.

### Clients

| Method | Endpoint | Description |
|---|---|---|
| GET | `/api/clients` | Retrieve all clients using JDBC |
| GET | `/api/clients/{id}` | Retrieve a client by ID |

## Error Handling

The application uses a global exception handler implemented with `@RestControllerAdvice`.

For example, attempting to create an application for a non-existent client returns HTTP `404 Not Found` with a JSON error response.

Example:

```json
{
  "timestamp": "2026-11-06T00:12:05",
  "status": 404,
  "error": "Not Found",
  "message": "Client not found"
}
```

## Getting Started

### Prerequisites

Make sure the following software is installed:

- Java 17 or later
- Maven
- Docker Desktop
- Git

### 1. Clone the repository

```bash
git clone https://github.com/TheNija172/bank-applications.git
cd bank-applications
```

### 2. Start PostgreSQL

The project uses Docker Compose to run PostgreSQL 18.4.

Start the database container:

```bash
docker compose up -d
```

Check the container status:

```bash
docker compose ps
```

The default database configuration in `docker-compose.yml` is:

| Setting | Value |
|---|---|
| Database | `mydatabase` |
| Username | `myuser` |
| Password | `secret` |
| Host port | `49542` |
| Container port | `5432` |

Make sure the database connection settings in `application.properties` match the Docker Compose configuration.

### 3. Configure the application

The default database connection settings in `src/main/resources/application.properties` are:

```properties
server.port=8081

spring.datasource.url=jdbc:postgresql://localhost:49542/mydatabase
spring.datasource.username=myuser
spring.datasource.password=secret
spring.datasource.driver-class-name=org.postgresql.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

If you change the database credentials, update both `docker-compose.yml` and `application.properties` accordingly.

The credentials above are intended for local development. Use your own secure credentials in other environments.

### 4. Build the application

Run the following Maven command:

```bash
mvn clean package
```

### 5. Run the application

Start Spring Boot:

```bash
mvn spring-boot:run
```

### 6. Open the application

Once the application has started, open:

`http://localhost:8081/`

The web interface allows bank employees to create credit applications, view existing records, and calculate preliminary credit scores.

---

