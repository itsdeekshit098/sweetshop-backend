# 🍬 Sweet Shop Management System (Backend)

hosted on:
https://sweetshop-frontend-git-main-deekshiths-projects-eaba341b.vercel.app/

https://sweetshop-frontend-21ugclsab-deekshiths-projects-eaba341b.vercel.app/

A **Spring Boot 3.x** application providing a REST API for managing a sweet shop.  
It supports **user authentication (JWT)**, **CRUD for sweets**, and **inventory operations** like purchase & restock.

---

## ✨ Highlights & Features

- ✅ **Production-grade structure** (entities, DTOs, controllers, services, repositories)
- 🔒 **JWT-based authentication** with role-based method security (`ROLE_USER`, `ROLE_ADMIN`)
- 🧪 **TDD workflow** — tests-first (Red-Green-Refactor)
- 📦 **Docker-ready** (`Dockerfile` + `docker-compose` example)
- 🔁 **Global exception handler** for consistent API errors
- 🧭 **Clear REST API surface** with request/response examples
- 🗂️ **Role-based access control**
    - `ROLE_USER` → browse, search, purchase sweets
    - `ROLE_ADMIN` → full CRUD & restock sweets

---

## 🛠 Tech Stack

- **Java 17**
- **Spring Boot 3.x**
- **Spring Data JPA** (Hibernate)
- **PostgreSQL** (production), **H2** (tests)
- **JJWT** for JWT token handling
- **Maven** for build automation
- **Lombok** for boilerplate reduction

---

## ⚡ Quickstart (Local Dev)

### ✅ Prerequisites
- Java 17+
- Maven 3.8+ (`./mvnw` included)
- PostgreSQL (or Docker)
- Node.js (only if running frontend)

---

### 🔧 Setup Environment Variables

You can set env vars or configure `application.yml`.

```bash
export SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/sweetshop
export SPRING_DATASOURCE_USERNAME=postgres
export SPRING_DATASOURCE_PASSWORD=postgres-password
export APP_JWT_SECRET=very-secret-key-of-32-chars
export APP_JWT_EXPIRATION_MS=3600000


▶️ Run Locally
./mvnw spring-boot:run
