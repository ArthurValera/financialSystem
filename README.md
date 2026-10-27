# Financial Management System

This project is an API for **personal financial control**, enabling a user to manage their own financial transactions (revenues and expenses), recurring transactions, categories, and balance projections. It is structured following a layered approach, including **controllers**, **services**, **repositories**, **models**, and **DTOs**, ensuring organization and ease of maintenance.

The actual Maven project lives in the [`financial-system/`](financial-system) subdirectory of this repository.

---

## 📋 Features

### 💰 **Financial Transactions**
- Register one-off transactions as **revenues (INCOME)** or **expenses (EXPENSE)**.
- Associate transactions with a category.
- Record transaction descriptions, due date, payment date, notes, and amount.

### 🔁 **Recurring Transactions**
- Register transactions that repeat on a **weekly**, **monthly**, or **yearly** basis, with an optional end date.
- Used as the basis for balance projections (see below).

### 📈 **Balance Projection**
- Project a future balance as of a given date, combining:
  - the settled balance (transactions already paid, `paymentDate <= today`),
  - one-off transactions already scheduled but not yet due, and
  - occurrences of active recurring transactions between today and the target date.

### 🗂️ **Category Management**
- Register and manage categories to classify transactions.
- Retrieve all categories for selection when registering a transaction.

### 👤 **Person / Account Management**
- Register an account (name, email, password, address).
- Update your own profile or deactivate your own account.

### 🔐 **Security**
- **Spring Security** with stateless **JWT** authentication (`com.auth0:java-jwt`).
- Passwords hashed with **BCrypt**.
- Every resource (transactions, recurring transactions, projections, your own profile) is scoped to the authenticated caller — there is no way to read or modify another user's data through the API.

### ✔️ **Validation and Consistency**
- Required fields validated with Bean Validation annotations.
- Relationships between entities (e.g. transactions and categories) validated before persisting.
- Soft delete: records are deactivated (`active = false`) rather than removed from the database.

---

## 🗂️ Project Structure

The code organization follows a modular and layered structure under `com.financial.system.financial.system`:

### **Package `controller`**
Exposes REST endpoints to manage system resources:
- `PersonController`, `TransactionController`, `RecurringTransactionController`, `CategoryController`, `ProjectionController`

### **Package `service`**
Implements business rules and orchestrates operations:
- `PersonService`, `TransactionService`, `RecurringTransactionService`, `CategoryService`
- `service/projection/ProjectionCalculator`: combines settled balance, scheduled transactions, and recurring occurrences into a projected balance.
- `service/recurrence/`: a `RecurrencePolicy` strategy per `RecurrenceType` (`Weekly`/`Monthly`/`YearlyRecurrencePolicy`), used to generate occurrence dates for recurring transactions.

### **Package `repository`**
Handles data persistence (Spring Data JPA):
- `PersonRep`, `TransactionRep`, `RecurringTransactionRep`, `CategoryRep`

### **Package `model`**
Defines the domain entities:
- `Person`, `Transaction`, `RecurringTransaction`, `Category`, `Address` (embedded), plus the `TransactionType` and `RecurrenceType` enums.

### **Package `dto`**
Data Transfer Objects (Java records) for requests and responses — a `*CreateDTO`/`*UpdateDTO` per writable resource and a `*ListingDTO`/`*DetailDTO` per read view.

### **Package `infra`**
Cross-cutting concerns:
- `infra/security/`: JWT issuing/verification (`TokenService`), the request filter that authenticates each call (`SecurityFilter`), the security rules (`SecurityConfig`), and the `UserDetails` adapter over `Person` (`PersonDetails`).
- `infra/exception/ErrorHandler`: maps domain exceptions to HTTP error responses.
- `infra/doc/SpringDocConfig`: OpenAPI/Swagger configuration.

### **Additional Components**
- `FinancialSystemApplication`: Spring Boot entry point.
- `application.properties`: configuration for the database and JWT secret. Contains no secrets — every sensitive value is read from an environment variable (see below).
- `db/migration`: Flyway migration scripts.

---

## 🚀 Technologies Used

- **Java 21**
- **Spring Boot 3** (Web, Data JPA, Validation, Security)
- **Spring Data JPA** — ORM and repository abstraction.
- **Spring Security** + **auth0 java-jwt** — stateless JWT authentication.
- **Flyway** — database migration management.
- **MySQL** — relational database.
- **springdoc-openapi** — Swagger UI / OpenAPI docs.
- **Maven** — dependency management and build tool.
- **Docker / Docker Compose** — containerized app + MySQL.

---

## ⚙️ Configuration

`financial-system/src/main/resources/application.properties` is committed and contains no secrets — it reads everything sensitive from environment variables:

| Variable      | Required | Default                                              | Purpose                          |
|---------------|----------|------------------------------------------------------|----------------------------------|
| `DB_PASSWORD` | yes      | —                                                    | MySQL password                   |
| `JWT_SECRET`  | yes      | —                                                    | HMAC secret used to sign JWTs    |
| `DB_URL`      | no       | `jdbc:mysql://localhost:3306/financialsystem_api`    | JDBC URL                         |
| `DB_USERNAME` | no       | `root`                                               | MySQL user                       |

Flyway creates the schema automatically on startup — do **not** set `spring.jpa.hibernate.ddl-auto=update`, it would fight with the Flyway migrations.

---

## 🐳 How to Run with Docker (recommended)

Runs both the API and MySQL in containers — no local Java, Maven, or MySQL needed.

### Prerequisites
- [Docker Desktop](https://www.docker.com/products/docker-desktop/) (running)

### Steps

1. **Clone this repository**
   ```bash
   git clone https://github.com/ArthurValera/financialSystem.git
   cd "financial system"
   ```
   Docker commands run from the repository root (where `docker-compose.yml` is).

2. **Create your `.env`** from the template and fill in both values:
   ```bash
   cp .env.example .env
   ```
   ```properties
   DB_PASSWORD=<any strong password>
   JWT_SECRET=<a long random string>
   ```
   `.env` is git-ignored. Generate values with e.g. `openssl rand -hex 32`.

3. **Build and start**
   ```bash
   docker compose up -d --build
   ```
   The API waits for MySQL to be healthy, then Flyway applies the migrations.

4. **Explore the API** at `http://localhost:8080/swagger-ui.html`.

### Useful commands
```bash
docker compose logs -f api     # follow API logs (or: docker logs -f financial-api)
docker compose ps              # container status
docker compose up -d --build   # rebuild after code changes
docker compose down            # stop (database data is kept in the db_data volume)
docker compose down -v         # stop AND wipe the database
docker compose up -d db        # run only MySQL, then run the API from your IDE
```

MySQL is exposed on **`localhost:3307`** (user `root`, password = your `DB_PASSWORD`) so it does not clash with a local MySQL on 3306.

---

## 📂 How to Run Locally (without Docker)

### Prerequisites
- Java 21
- MySQL installed and running (or just the Docker `db` service — see above)
- Maven (or use the bundled `mvnw`/`mvnw.cmd` wrapper — no local Maven install required)

### Steps

1. **Create the database** (skip if using the Docker `db` service)
   ```sql
   CREATE DATABASE financialsystem_api;
   ```

2. **Set the environment variables** `DB_PASSWORD` and `JWT_SECRET` (in IntelliJ: *Run → Edit Configurations → Environment variables*). If you're using the Docker `db` service, also set `DB_URL=jdbc:mysql://localhost:3307/financialsystem_api`.

3. **Run the project** from `financial-system/`
   ```bash
   ./mvnw spring-boot:run
   ```
   (use `mvnw.cmd` on Windows)

4. **Explore the API** at `http://localhost:8080/swagger-ui.html`.

---

## 📑 Main Endpoints

**Auth**
- `POST /auth/login` — authenticate with email/password, returns a JWT.

**Person** (`/person`)
- `POST /person` — register an account. *(public)*
- `GET /person` — list registered people.
- `PUT /person` — update your own profile.
- `DELETE /person` — deactivate your own account.

**Categories** (`/categories`)
- `POST /categories` — register a category.
- `GET /categories` — list categories.

**Transactions** (`/transactions`)
- `POST /transactions` — register a transaction, attributed to the authenticated caller.
- `GET /transactions` — list your own transactions.
- `PUT /transactions` — update one of your own transactions.
- `DELETE /transactions/{id}` — deactivate one of your own transactions.

**Recurring Transactions** (`/recurring-transactions`)
- `POST /recurring-transactions` — register a recurring transaction.
- `GET /recurring-transactions` — list your own recurring transactions.
- `PUT /recurring-transactions/{id}` — update one of your own recurring transactions.
- `DELETE /recurring-transactions/{id}` — deactivate one of your own recurring transactions.

**Projection** (`/projection`)
- `GET /projection?until=YYYY-MM-DD` — project your balance as of the given date.

All endpoints except `POST /auth/login` and `POST /person` require an `Authorization: Bearer <token>` header.

## 📧 Contact

If you have questions or suggestions, feel free to reach out:

*   **Author**: Arthur Valera de Castro Guerra
*   **Email**: arthurvaleradev@gmail.com
*   **LinkedIn**: [Arthur Valera](https://www.linkedin.com/in/arthur-valera-64352a210/)

---