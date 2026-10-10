# College Parking Management System - Java Full Stack

## Project structure

```text
College-Parking-Management/
├── frontend/   # React + CSS + Vite
└── backend/    # Java + Spring Boot + Maven + MySQL
```

## 1. Backend

Open a terminal in `backend`:

```bash
mvn spring-boot:run
```

Backend runs at `http://localhost:8080`.

Java: 17+

Database: MySQL. The application creates/updates the `college_parking` database automatically when the MySQL user has permission. Default credentials are `root` / `root`; change them in `backend/src/main/resources/application.properties` or set `DB_USERNAME` and `DB_PASSWORD`.

## 2. Frontend

Open a second terminal in `frontend`:

```bash
npm install
npm run dev
```

Open the Vite URL shown in the terminal (normally `http://localhost:5173`).

## 3. Architecture

React + CSS -> REST API -> Spring Boot -> Spring Data JPA -> MySQL

The frontend and backend are intentionally separate folders so the project can be opened and developed easily in VS Code.
