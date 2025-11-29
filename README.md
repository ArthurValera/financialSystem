# Financial Management System

This project is an API for **personal financial control**, allowing you to manage people, one-time transactions, recurring transactions and categories. It is structured following a layered approach, including **controllers**, **services**, **repositories**, **models**, and **DTOs**, ensuring organization and ease of maintenance.

---

## 📋 Features

### 💰 **Financial Transactions**
- Register transactions as **revenues** or **expenses**.
- Associate transactions with a category and a person.
- Record transaction descriptions, dates, amounts, and status.

### 💰 **Recurring Transactions**
- Register recurring transactions, such as subscriptions, monthly fees, or installments.
- Create recurring incomes or expenses
- Support for MONTHLY, WEEKLY, YEARLY recurrence
- Infinite or limited recurrence (start/end dates)
- Automatic projection and calculation
- Manual generation of new recurring entries
- Pagination and filtering for recurring transactions

  
   ```bash
   GET /projection/{personId}?until=YYYY-MM-DD
   ```

### 🗂️ **Category Management**
- Register and manage categories to classify transactions.
- Retrieve all categories for selection in transaction registration.

### 👤 **Person Management**
- Register people (clients, suppliers, or other contacts) involved in transactions.
- Manage personal data like name and active status.

### ✔️ **Validation and Consistency**
- Ensure all required fields are completed.
- Validate relationships between entities (e.g., transactions and people, transactions and categories).
- Input validation with Bean Validation annotations.

### 🔐 **Security**
- The API includes a complete authentication system using Spring Security and JWT tokens.
- Login using email + password
- Password hashing using BCrypt
- Stateless authentication using JWT
- Custom filter validates token on every request
- Protected routes (except /auth/login and /person)

- Authentication flow:
  1. User logs in:
    ```bash
      POST /auth/login
    ```
  2. System returns:
     ```bash
       {
          "token": "JWT_TOKEN_HERE"
       }
      ```
  3. Use this token on all protected routes:
     ```bash
     Authorization: Bearer JWT_TOKEN_HERE
     ```
---

## 🗂️ Project Structure

The code organization follows a modular and layered structure:

### **Package `controller`**
Exposes REST endpoints to manage system resources:
- `CategoriaController`
- `LancamentoController`
- `PessoaController`

### **Package `service`**
Implements business rules and orchestrates operations:
- `CategoriaService`
- `LancamentoService`
- `PessoaService`

### **Package `repository`**
Handles data persistence:
- `CategoriaRep`
- `LancamentoRep`
- `PessoaRep`

### **Package `model`**
Defines the domain entities:
- `Categoria`
- `Lancamento`
- `Pessoa`

### **Package `dto`**
Data Transfer Objects for requests and responses:
- `CategoriaDTO`
- `LancamentoDTO`
- `PessoaDTO`
- `PessoaCreateDTO`
- `PessoaUpdateDTO`

### **Package `infra/security`**
 Security Components:
- `SecurityConfig`
- `SecurityFilter`
- `TokenService`
- `PersonDetailsService`
- 
### **Additional Components**
- `FinancialSystemApplication`: Spring Boot entry point.
- `application.properties`: Configuration file for the database and Hibernate.
- `db/migration`: Flyway migration scripts.

---

## 🚀 Technologies Used

- **Java 21**
- **Spring Boot 3**
- **Spring Data JPA**
- **Spring Security** 
- **Hibernate Validator**
- **Flyway**
- **MySQL**
- **Maven**

---

## 📂 How to Run the Project

### Prerequisites
- Java 17 installed.
- MySQL installed and configured.
- IDE with Java project support (IntelliJ IDEA, Eclipse, VS Code).
- Maven installed.

---

### Steps

1. **Clone this repository**
   ```bash
   git clone https://github.com/ArthurValera/financialSystem.git
   ```
2. **Configure your environment in application.properties**
   ```bash
    spring.datasource.url=jdbc:mysql://localhost:3306/financialsystem
    spring.datasource.username=YOUR_USER
    spring.datasource.password=YOUR_PASSWORD
   
    spring.jpa.hibernate.ddl-auto=update
   ```
    Please make sure the database financialSystem exist.

3. **Install dependencies:**
   ```bash
   mvn install
   ```
      
4. **Run application**
   ```bash
    mvn spring-boot:run
   ```
- API will be available at:

http://localhost:8080

Swagger UI: http://localhost:8080/swagger-ui.html


## 📑 Main Endpoints
**People**
- `POST /pessoas: Register a person.`

- `GET /pessoas: List all people.`

- `PUT /pessoas/{id}: Update a person.`

- `DELETE /pessoas/{id}: Delete a person.`

**Authentication**
-`POST /auth/login: Authenticate and receive a JWT token` 

**Categories**
- `POST /categorias: Register a category.`

- `GET /categorias: List categories.`

**Transactions**
- `POST /lancamentos: Register a transaction.`

- `GET /lancamentos: List transactions.`

- `GET /lancamentos/{id}: View transaction details.`

## 📧 Contact

If you have questions or suggestions, feel free to reach out:

*   **Author**: Arthur Valera de Castro Guerra
*   **Email**: arthurvalera7@gmail.com
*   **LinkedIn**: [Arthur Valera](https://www.linkedin.com/in/arthur-valera-64352a210/)

---
