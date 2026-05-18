# Election Vote Manager

Election Vote Manager is a Java-based web application for managing a simple election workflow end-to-end, including voter onboarding, authentication, ballot submission, and officer-side election operations.

## Project Overview

The project demonstrates:

- multi-module Java application structure with Gradle
- MVC web app patterns using Spring
- JSP/JSTL server-rendered pages
- MySQL-backed persistence with JPA repositories
- basic election administration operations (start/end election, result handling)

## Tech Stack

- Java 8
- Gradle (multi-project build)
- Spring Boot / Spring MVC / Spring Data
- JSP + JSTL
- MySQL

## Repository Structure

```text
election-vote-manager/
|- README.md
|- mss49.sql                            # SQL schema + seed data
|- election-vote-manager/              # Main Gradle multi-module project
   |- app/                              # Web application (controllers, services, repositories, views)
   |- list/                             # Custom linked-list module
   |- utilities/                        # Utility module used by app
   |- build-logic/                      # Convention plugins for build configuration
```

## Key Application Features

- **Voter flow**
  - registration page
  - login page
  - vote dashboard
- **Officer flow**
  - officer dashboard
  - start election
  - end election
  - view election results
  - winner/hung parliament declaration logic
- **Data model coverage**
  - voter, candidate, party, constituency, officer, election, UVC code

## UI Views

JSP views are located in:

- `election-vote-manager/app/src/main/webapp/WEB-INF/views/login.jsp`
- `election-vote-manager/app/src/main/webapp/WEB-INF/views/registration.jsp`
- `election-vote-manager/app/src/main/webapp/WEB-INF/views/votedashboard.jsp`
- `election-vote-manager/app/src/main/webapp/WEB-INF/views/officerdashboard.jsp`

## Prerequisites

- JDK 8 installed and configured (`JAVA_HOME`)
- MySQL server running locally
- Git (optional, for cloning/version control)

## Setup Instructions

### 1) Clone the repository

```bash
git clone <your-repo-url>
cd election-vote-manager
```

### 2) Create and seed the database

Import `mss49.sql` into MySQL:

```sql
SOURCE /absolute/path/to/election-vote-manager/mss49.sql;
```

### 3) Configure database connection

Update `election-vote-manager/app/src/main/resources/application.properties` as needed:

- `spring.datasource.url`
- `spring.datasource.username`
- `spring.datasource.password`
- `spring.datasource.driver-class-name`

Default values currently point to:

- URL: `jdbc:mysql://localhost/jpa?useSSL=false`
- username: `root`

Make sure the database name in the URL matches your imported schema.

### 4) Build the project

From `election-vote-manager/`:

```bash
# Windows
gradlew.bat build

# macOS/Linux
./gradlew build
```

### 5) Run the application

From `election-vote-manager/`:

```bash
# Windows
gradlew.bat :app:run

# macOS/Linux
./gradlew :app:run
```

After startup, open:

- `http://localhost:8080`

## Module Notes

- `app`: Spring web app with controllers, service layer, repository layer, and JSP views.
- `list`: standalone linked list implementation used to demonstrate core data structure implementation.
- `utilities`: helper utilities with dependency on `list`.
- `build-logic`: centralized Gradle convention plugins for consistent module config.

## Security and Data Notes

- Password handling includes hashing logic in parts of the codebase.
- Seed/test credentials and sample records are included in SQL for local development.
- Do not use seed credentials or default local DB settings in production.

## Known Improvements (Roadmap)

- improve package naming consistency across all Java classes
- add DTO/request validation and centralized exception handling
- introduce integration tests for critical election flows
- externalize secrets and database config via environment variables
- modernize Spring/Gradle dependency versions

