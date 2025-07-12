# DigitalTolk Translation API

A Spring Boot-based REST API for managing translation entries, built as part of a code test. The app supports full CRUD operations, search and filtering, JSON export, and token-based authentication. Built for scalability and maintainability using modern Java practices and Docker.

---

## 📦 Features

- ✅ Create, Read, Update, Delete (CRUD) translations
- 🔍 Search/filter by key, content, tag, locale
- 🗂️ Export translations as grouped JSON
- 🔐 JWT-based authentication
- 📄 Swagger UI for testing APIs
- 🐳 Docker & Docker Compose setup
- ✅ SOLID principle-driven architecture
- ⚡ Optimized database with indexing

---

## 🚀 Getting Started

### ✅ Prerequisites

- Java 17
- Maven
- Docker + Docker Compose

---

### 🔧 Option 1: Run with Docker Compose (Recommended)

```bash
mvn clean package -DskipTests
docker-compose up --build
```

Then open:

- Swagger UI: http://localhost:8090/swagger-ui/index.html

---

### 🔧 Option 2: Run Locally

```bash
docker run -d -p 3306:3306 --name mysql-db \
  -e MYSQL_ROOT_PASSWORD=root \
  -e MYSQL_DATABASE=digitaltolk \
  mysql:8

mvn spring-boot:run
```

App runs on: http://localhost:8090

---

## 🔐 Authentication

Login:

```json
POST /api/v1/auth/login

{
  "username": "admin",
  "password": "password"
}
```

- Copy the returned JWT token
- Click **Authorize** in Swagger
- Paste token to access secured endpoints

---

## 📘 API Docs

Swagger UI:  
[http://localhost:8090/swagger-ui/index.html](http://localhost:8090/swagger-ui/index.html)

- All endpoints documented
- Search, CRUD, Export, Auth all testable

---

## 🧠 Design Overview

- **Spring Boot 3 + Java 17**
- **MySQL** via JPA with native queries for search
- **Spring Security** with JWT tokens
- **Clean layers**: Controller → Service → Repository
- **SOLID Principles**:
    - SRP: Classes are small and focused
    - OCP: Extendable service layer
    - DIP: Constructor injection
- **Exception Handling**: Global handler with custom responses
- **Docker**: Lightweight containers for app and DB

---

## 💡 Design Decisions & Architecture

### 🔐 Authentication

- Stateless JWT with custom auth filter
- `/api/v1/auth/login` issues token
- Swagger supports authenticated testing

### 🔎 Search & Performance

- Native query with optional filters on:
    - `key`, `tags`, `content`
- Case-insensitive partial matching
- Used native SQL for control & performance

### ⚙️ Indexed Columns

| Column        | Purpose                       | Index Type         |
|---------------|-------------------------------|---------------------|
| `key`         | Fast exact match search        | `INDEX`             |
| `locale`      | Grouping for export/filter     | `INDEX`             |
| `tags`        | Partial `LIKE` tag filtering   | `FULLTEXT INDEX`    |

> Full-text indexes boost partial-match performance, meeting the `<200ms` search goal even with large datasets.

### 🗂️ JSON Export

- Export all or filtered data
- Grouped by locale
- Built-in streaming response (efficient on large data)

### 🧱 Scalable DB Schema

- Single `translations` table
- UUID-based IDs
- Easily extensible for multi-tenant or versioned translations

---

## 🛠 Technologies

- Java 17
- Spring Boot 3
- Spring Security + JWT
- Spring Data JPA
- MySQL 8
- Swagger (`springdoc-openapi`)
- Docker & Docker Compose

---

## ✅ Future Enhancements

- [ ] User roles and role-based access
- [ ] Rate limiting
- [ ] Caching frequently accessed translations
- [ ] Admin dashboard for managing keys/locales

---

## 📄 License

MIT — use freely for demo or educational purposes.