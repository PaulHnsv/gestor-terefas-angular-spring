# Gestor de Tarefas

> A full-stack task management application built with **Spring Boot** and **Angular** as a portfolio project to demonstrate skills in REST API design, hexagonal architecture, JWT authentication, and modern frontend development.

## Table of Contents

- [Overview](#overview)
- [Tech Stack](#tech-stack)
- [Architecture](#architecture)
- [API Endpoints](#api-endpoints)
- [Getting Started](#getting-started)
- [Project Structure](#project-structure)

---

## Overview

Gestor de Tarefas lets users create and organize projects and tasks. Each task belongs to a project and carries a priority level and a status (Pending, In Progress, or Done). Users authenticate via JWT and all task/project data is scoped to the authenticated session.

**Key features:**

- User registration and login with JWT-based authentication
- Full CRUD for projects
- Full CRUD for tasks, including a dedicated "conclude" action
- Task filtering by project
- Reactive Angular frontend with form state management (idle / loading / success / error)

---

## Tech Stack

### Backend

| Layer | Technology |
|---|---|
| Language | Java 17 |
| Framework | Spring Boot 4.0.1 |
| Security | Spring Security + JWT (jjwt 0.11.5) |
| Persistence | Spring Data JPA + H2 (in-memory) |
| Mapping | MapStruct 1.5.5 |
| Boilerplate | Lombok |
| Build | Maven 3.x |

### Frontend

| Layer | Technology |
|---|---|
| Framework | Angular 21.1.0 |
| Language | TypeScript |
| Styling | SCSS + Bootstrap 5.3.8 |
| Icons | Bootstrap Icons 1.13.1 |
| HTTP / Async | RxJS 7.8, Angular HttpClient |

---

## Architecture

The backend follows **Hexagonal Architecture** (Ports & Adapters), organised by feature slice.

```
features/
├── projects/
│   ├── domain/
│   │   ├── model/          # Project (pure domain object)
│   │   └── ports/
│   │       ├── inbound/    # Use-case interfaces (CreateProjectUseCase, etc.)
│   │       └── outbound/   # Repository interface
│   ├── application/
│   │   ├── dto/            # Request / Response DTOs + MapStruct mapper
│   │   └── service/        # Use-case implementations
│   └── adapters/
│       ├── inbound/rest/   # ProjectController (REST)
│       └── outbound/persistence/  # JPA entity + repository adapter
└── tasks/                  # Same structure as projects
```

**Why hexagonal?**
The domain layer has zero framework dependencies. Swapping the database (e.g. H2 → PostgreSQL) or the transport layer (REST → gRPC) only requires touching the adapters — the domain and application layers stay unchanged.

The `infra/` package holds cross-cutting concerns shared across features: user persistence, JWT filter, and `CustomUserDetailsService`.

---

## API Endpoints

All endpoints except `/auth/**` require the `Authorization: Bearer <token>` header.

### Auth

| Method | Path | Description |
|---|---|---|
| `POST` | `/auth/register` | Create a new user account |
| `POST` | `/auth/login` | Authenticate and receive a JWT |

### Projects

| Method | Path | Description |
|---|---|---|
| `GET` | `/api/projects` | List all projects |
| `POST` | `/api/projects` | Create a project |
| `GET` | `/api/projects/{id}` | Get a project by ID |
| `PUT` | `/api/projects/{id}` | Update a project |
| `DELETE` | `/api/projects/{id}` | Delete a project |

### Tasks

| Method | Path | Description |
|---|---|---|
| `GET` | `/api/tasks` | List all tasks |
| `POST` | `/api/tasks` | Create a task |
| `GET` | `/api/tasks/{id}` | Get a task by ID |
| `GET` | `/api/tasks/{idProject}` | List tasks by project |
| `PUT` | `/api/tasks/{id}` | Update a task |
| `DELETE` | `/api/tasks/{id}` | Delete a task |
| `PATCH` | `/api/tasks/{id}` | Mark a task as concluded |

> **H2 Console** is available at `http://localhost:8080/h2` (JDBC URL: `jdbc:h2:mem:gestor_tarefas`).

---

## Getting Started

### Prerequisites

- Java 17+
- Maven 3.x
- Node.js 18+ and npm

### 1. Clone the repository

```bash
git clone https://github.com/your-username/gestor-terefas-angular-spring.git
cd gestor-terefas-angular-spring
```

### 2. Run the backend

```bash
cd backend
./mvnw spring-boot:run
```

The API will be available at `http://localhost:8080`.

### 3. Run the frontend

```bash
cd frontend
npm install
ng serve
```

The app will be available at `http://localhost:4200`.

> The frontend is pre-configured to proxy API calls to `http://localhost:8080`, so no CORS setup is needed during local development.

---

## Project Structure

```
gestor-terefas-angular-spring/
├── backend/                    # Spring Boot application
│   └── src/main/java/com/paulo/gestortarefas/
│       ├── config/             # CORS, H2 console, Security config
│       ├── features/
│       │   ├── projects/       # Project feature (hex architecture)
│       │   └── tasks/          # Task feature (hex architecture)
│       ├── infra/              # User persistence & JWT infrastructure
│       └── shared/             # Auth controller, JWT service, enums, error handling
└── frontend/                   # Angular application
    └── src/app/
        ├── core/               # Guards, interceptors, models, auth service
        ├── features/
        │   ├── auth/           # Login and register pages
        │   ├── home/           # Dashboard / status page
        │   ├── projeto/        # Project list, create, edit, delete
        │   └── tarefa/         # Task list, create, edit, delete
        └── shared/             # Navbar, interfaces, types
```
