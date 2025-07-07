# Financial Management System

This project is an API for **financial control**, enabling the management of people, financial transactions (revenues and expenses), and categories. It is structured following a layered approach, including **controllers**, **services**, **repositories**, **models**, and **DTOs**, ensuring organization and ease of maintenance.

---

## 📋 Features

### 💰 **Financial Transactions**
- Register transactions as **revenues** or **expenses**.
- Associate transactions with a category and a person.
- Record transaction descriptions, dates, amounts, and status.

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

### **Additional Components**
- `FinancialSystemApplication`: Spring Boot entry point.
- `application.properties`: Configuration file for the database and Hibernate.
- `db/migration`: Flyway migration scripts.

---

## 🚀 Technologies Used

- **Java 17**: Main language.
- **Spring Boot**: Framework for rapid backend development.
- **Spring Data JPA**: ORM and repository abstraction.
- **Hibernate Validator**: Input validation.
- **Flyway**: Database migration management.
- **MySQL**: Relational database.
- **Maven**: Dependency management and build tool.

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
2. **Navigate to the project directory**
   ```bash
   cd financialSystem
   ```
3. **Open src/main/resources/application.properties and adjust credentials:**
   ```bash
   spring.datasource.url=jdbc:mysql://localhost:3306/financialsystem
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   spring.jpa.hibernate.ddl-auto=update
   ```
   Please make sure the database financialSystem exist.
   
4. **Install dependencies**
   ```bash
    mvn install
   ```
5. Run the project and access the API in your browser in localhost

## 📑 Main Endpoints
**People**
- `POST /pessoas: Register a person.`

- `GET /pessoas: List all people.`

- `PUT /pessoas/{id}: Update a person.`

- `DELETE /pessoas/{id}: Delete a person.`

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
