# project-management-api

Java SpringBoot based backend api to manages projects and their associated tasks



## Setup Instructions

---

## Database Schema
PostgreSQL is used to store data for Projects and tasks. Given Below is the ER Diagram and relational schema.

### Schema
Using JPA/Hibernate to manage the schema. Given below is the raw SQL.

-- Creating the Projects table
CREATE TABLE projects (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    status ENUM('planning', 'active', 'completed', 'on_hold') NOT NULL,
    createdat TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Creating the Tasks table
CREATE TABLE tasks (
    id INT PRIMARY KEY AUTO_INCREMENT,
    project_id INT,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    status ENUM('pending', 'in_progress', 'completed') NOT NULL,
    priority ENUM('low', 'medium', 'high') NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (project_id) REFERENCES projects(id) ON DELETE CASCADE
);


## API Documentation

### Project Endpoints

#### **GET /api/projects**
- **Description**: Get all projects.
- **Response**:

#### **GET /api/projects/:id**
- **Description**: Get project by ID.
- **Response**:

#### **POST /api/projects**
- **Description**: Create a new project.
- **Body**:
- **Response**:
      
#### **PUT /api/projects/:id**
- **Description**: Update an existing project by ID.
- **Body**: 
- **Response**:

#### **DELETE /api/projects/:id**
- **Description**: Delete a project by ID.
- **Response**:

---

### Task Endpoints

#### **GET /api/tasks**
- **Description**: Get all tasks.
- **Response**:

#### **GET /api/tasks/:id**
- **Description**: Get task by ID.
- **Response**:

#### **POST /api/tasks**
- **Description**: Create a new task.
- **Body**: 
- **Response**:

#### **PUT /api/tasks/:id**
- **Description**: Update a task by ID.
- **Body**: 
- **Response**:

#### **DELETE /api/tasks/:id**
- **Description**: Delete a task by ID.
- **Response**:

---

### Additional Endpoints

#### **GET /api/projects/:id/tasks**
- **Description**: Get all tasks for a specific project.
- **Response**:

#### **GET /api/projects/with-tasks**
- **Description**: Get all projects with their tasks.
- **Response**:

#### **GET /api/tasks/with-projects**
- **Description**: Get all tasks with project info.
- **Response**:


## Structure

Porject is structure using Layered Architecture as follow:

- **Controller:**
  Act as an entry point for HTTP requests. Handles routing, request mapping and calls the appropriate service layer.
  
- **Service:**
  Contains the business logic of the application. Processes data, applies business rules, and manages transactions.
  
- **Repository:**
  Manages database interactions. Responsible for CRUD (Create, Read, Update, Delete) operations and querying the database.
  
- **Model:**
  Represent the structure of the Data. Encapsulates the data and the business logic that belongs to the data


## Dependencies

- **Lombok Developer Tools**
  reduce boilerplate code
- **Spring Boot DevTools Developer Tools**
  Provides fast application restarts, LiveReload, and configurations for enhanced development experience.
- **Spring Data JPA SQL**
  Persist data in SQL stores with Java Persistence API using Spring Data and Hibernate.
- **Spring Web**
  Build web, including RESTful, applications using Spring MVC. Uses Apache Tomcat as the default embedded container.
- **Validation I/O**
  Bean Validation with Hibernate validator.
- **H2 Database SQL**
  Provides a fast in-memory database that supports JDBC API and R2DBC access, with a small (2mb) footprint. Supports embedded and server modes as well as a browser based console application.
- **SpringDoc OpenAPI Starter WebMVC UI**
  API Documentation using OpenAPI standards

## Other Resources

[▶️ Tutorial for learning SpringBoot](https://www.youtube.com/watch?v=-Fe0zk-F4OA)

