# Project Management API

Java SpringBoot based backend api to manages projects and their associated tasks

## Setup Instructions

The application can be run in a containerized environment using Docker Compose. The latest application build (`.jar`) is already present in the `target/` folder and can be used to build a Docker image.

### Steps to Run the Application

1. **Clone the Repository**  
   Clone the repository to your local machine and unzip it if necessary.

2. **Navigate to the Root Folder**  
   Open a terminal in the root folder that contains the `Dockerfile` and `docker-compose.yml`.

3. **Build the Docker Image**  
   Run the following command to build the Docker image:

   ```bash
   docker build -t project-management-api-app .

4. **Start the Containers**
   Start the containers using Docker Compose:
   ```bash
      docker-compose up --build

5. **Endpoints**
   If the setup is successful, the following services will be available:
   
   Database: `localhost:5435`
   
   Web Server: `localhost:8082`

6. **Database Access**
   
    Database: PostgreSQL

    Admin Tool: PgAdmin (recommended)
    
    Credentials:
    
    Username: `sukhveer`
    
    Password: `bitsql`

7. **Insert Test Data**
    Once inside PgAdmin, you can run the following SQL script to insert sample data into the database.
    Note: Table creation is automatically handled by Spring Boot JPA.

```sql
INSERT INTO Projects (id, name, description, status, createdat, updated_at)
VALUES
(1, 'Project Alpha', 'A new product development project for Q3.', 'active', '2025-06-01 08:00:00', '2025-06-01 08:30:00'),
(2, 'Project Beta', 'Research and development of a new mobile app.', 'planning', '2025-05-20 09:00:00', '2025-06-10 11:00:00'),
(3, 'Project Gamma', 'Update the company website with new features.', 'completed', '2025-03-15 10:00:00', '2025-05-10 14:00:00'),
(4, 'Project Delta', 'Company-wide internal system upgrade.', 'on_hold', '2025-04-10 12:00:00', '2025-04-12 16:00:00'),
(5, 'Project Epsilon', 'Internal rebranding project for the marketing team.', 'active', '2025-06-05 13:00:00', '2025-06-05 13:30:00');
```
```sql
INSERT INTO Tasks (id, project_id, title, description, status, priority, created_at, updated_at)
VALUES
(1, 1, 'Task 1: Market Research', 'Research on current market trends for the product.', 'in_progress', 'high', '2025-06-01 09:00:00', '2025-06-01 09:30:00'),
(2, 1, 'Task 2: Product Design', 'Initial wireframes for the product prototype.', 'pending', 'medium', '2025-06-01 09:30:00', '2025-06-01 09:45:00'),
(3, 2, 'Task 1: Competitive Analysis', 'Analyze competitors in the mobile app industry.', 'pending', 'high', '2025-05-20 09:30:00', '2025-05-20 10:00:00'),
(4, 2, 'Task 2: Feature Planning', 'Define core features for the new mobile app.', 'in_progress', 'medium', '2025-05-21 10:00:00', '2025-05-21 10:30:00'),
(5, 3, 'Task 1: Redesign Homepage', 'Redesign homepage UI to improve user engagement.', 'completed', 'high', '2025-03-16 11:00:00', '2025-03-17 12:00:00'),
(6, 3, 'Task 2: Content Update', 'Update website content for new product launch.', 'completed', 'medium', '2025-04-01 10:00:00', '2025-04-02 11:00:00'),
(7, 4, 'Task 1: Requirements Gathering', 'Gather requirements for the new system update.', 'pending', 'low', '2025-04-10 13:00:00', '2025-04-10 13:30:00'),
(8, 5, 'Task 1: Design Branding Assets', 'Design new logo and branding elements.', 'in_progress', 'high', '2025-06-05 14:00:00', '2025-06-05 14:30:00'),
(9, 5, 'Task 2: Internal Communication', 'Develop internal communication strategy for rebranding.', 'pending', 'medium', '2025-06-06 09:00:00', '2025-06-06 09:15:00');
```

---

## Database Schema
The database schema is managed via **Spring Data JPA and Hibernate**, which automatically generates the required tables based on entity classes. For reference or manual setup, below is the raw SQL equivalent of the schema:

### ER Diagram
![App Screenshot](assets/er_digram.png)

### Schema
Using JPA/Hibernate to manage the schema. Given below is the raw SQL.
   ```sql
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
```


## API Documentation

### Project Endpoints

### **GET /api/projects**
- **Description**: Get all projects.
- **Response**:
    - **Status Code**: `200 OK`
    - **Body**: List of projects.
      ```json
      [
        {
          "id": 1,
          "name": "Project Alpha",
          "description": "A new product development project for Q3.",
          "status": "active",
          "createdAt": "2025-06-01T08:00:00",
          "updatedAt": "2025-06-01T08:30:00",
          "taskIds": [
               1,
               2
           ]
        },
        {
          "id": 2,
          "name": "Project Beta",
          "description": "Research and development of a new mobile app.",
          "status": "planning",
          "createdAt": "2025-05-20T09:00:00",
          "updatedAt": "2025-06-10T11:00:00",
          "taskIds": [
               1,
               2
           ]
        },
        ...
      ]
      ```

### **GET /api/projects/:id**
- **Description**: Get project by ID.
- **Response**:
    - **Status Code**: `200 OK`
    - **Body**: Single project object.
      ```json
         {
        "id": 3,
        "name": "Project Gamma",
        "description": "Update the company website with new features.",
        "status": "COMPLETED",
        "createdAt": "2025-03-15T10:00:00",
        "updatedAt": "2025-05-10T14:00:00",
        "taskIds": [
               1,
               2
           ]
      }
      ```
    - **Error Code**: `404 Not Found` (If project not found)

### **POST /api/projects**
- **Description**: Create a new project.
- **Body**: 
    ```json
    {
     "name": "Project Zeta",
     "description": "A new AI research initiative.",
     "status": "PLANNING"
    }
    ```
- **Response**:
    - **Status Code**: `201 Created`
    - **Body**: Created project object.
    - **Error Code**: `400 Bad Request` (If validation fails)

### **PUT /api/projects/:id**
- **Description**: Update an existing project by ID.
- **Body**: 
    ```json
    {
     "name": "Project Alpha - Revamped",
     "description": "Updated scope for Q3 launch.",
     "status": "ACTIVE"
   }
    ```
- **Response**:
    - **Status Code**: `200 OK`
    - **Body**: Updated project object.
    - **Error Code**: `404 Not Found` (If project not found)

### **DELETE /api/projects/:id**
- **Description**: Delete a project by ID.
- **Response**:
    - **Status Code**: `204 No Content`
    - **Error Code**: `404 Not Found` (If project not found)

---

## Task Endpoints

### **GET /api/tasks**
- **Description**: Get all tasks.
- **Response**:
    - **Status Code**: `200 OK`
    - **Body**: List of tasks.
      ```json
      [
        {
        "id": 1,
        "title": "Design database schema",
        "description": null,
        "status": "PENDING",
        "priority": "HIGH",
        "createdAt": "2025-06-23T04:23:34.682834",
        "updatedAt": "2025-06-23T04:23:34.682834"
       },
        ...
      ]
      ```

### **GET /api/tasks/:id**
- **Description**: Get task by ID.
- **Response**:
    - **Status Code**: `200 OK`
    - **Body**: Single task object.
      ```json
      {
        "id": 1,
        "title": "Design database schema",
        "description": null,
        "status": "PENDING",
        "priority": "HIGH",
        "createdAt": "2025-06-23T04:23:34.682834",
        "updatedAt": "2025-06-23T04:23:34.682834"
       }
      ```
    - **Error Code**: `404 Not Found` (If task not found)

### **POST /api/tasks**
- **Description**: Create a new task.
- **Body**: 
    ```json
    {
       "title": "Design API structure",
       "description": "Define routes and HTTP methods for project and task management.",
       "status": "PENDING",
       "priority": "HIGH",
       "project": {
           "id": 3
       }
   }
    ```
- **Response**:
    - **Status Code**: `201 Created`
    - **Body**: Created task object.
    - **Error Code**: `400 Bad Request` (If validation fails)

### **PUT /api/tasks/:id**
- **Description**: Update a task by ID.
- **Body**: 
    ```json
    {
        "title": "UX Testing",
        "status": "COMPLETED",
        "priority": "HIGH"
    }
    ```
- **Response**:
    - **Status Code**: `200 OK`
    - **Body**: Updated task object.
    - **Error Code**: `404 Not Found` (If task not found)

### **DELETE /api/tasks/:id**
- **Description**: Delete a task by ID.
- **Response**:
    - **Status Code**: `204 No Content`
    - **Error Code**: `404 Not Found` (If task not found)

---

## Additional Endpoints

### **GET /api/projects/:id/tasks**
- **Description**: Get all tasks for a specific project.
- **Response**:
    - **Status Code**: `200 OK`
    - **Body**: List of tasks for the project.
    - **Error Code**: `404 Not Found` (If project not found)

### **GET /api/projects/with-tasks**
- **Description**: Get all projects with their tasks.
- **Response**:
    - **Status Code**: `200 OK`
    - **Body**: List of projects with nested tasks.

### **GET /api/tasks/with-projects**
- **Description**: Get all tasks with project info.
- **Response**:
    - **Status Code**: `200 OK`
    - **Body**: List of tasks with associated project info.

---

  

## Structure

Porject is structure using Layered Architecture as follow:

- **Controller:**
     - Act as an entry point for HTTP requests. Handles routing, request mapping and calls the appropriate service layer.
  
- **Service:**
     - Contains the business logic of the application. Processes data, applies business rules, and manages transactions.
  
- **Repository:**
     - Manages database interactions. Responsible for CRUD (Create, Read, Update, Delete) operations and querying the database.
  
- **Model:**
     - Represent the structure of the Data. Encapsulates the data and the business logic that belongs to the data
- **Exception Handling and DTO:**
     - Exceptions are handled in one place using a class called GlobalExceptionHandler.java. This helps catch and manage errors across the whole application in a consistent way.
  DTO is used to prevent circular JSON serialization errors in join queries


## Dependencies

- **Lombok Developer Tools**
     - reduce boilerplate code
- **Spring Boot DevTools Developer Tools**
     - Provides fast application restarts, LiveReload, and configurations for enhanced development experience.
- **Spring Data JPA SQL**
     - Persist data in SQL stores with Java Persistence API using Spring Data and Hibernate.
- **Spring Web**
     - Build web, including RESTful, applications using Spring MVC. Uses Apache Tomcat as the default embedded container.
- **Validation I/O**
     - Bean Validation with Hibernate validator.
- **H2 Database SQL**
     - Provides a fast in-memory database that supports JDBC API and R2DBC access, with a small (2mb) footprint. Supports embedded and server modes as well as a browser based console application.
- **SpringDoc OpenAPI Starter WebMVC UI**
     - API Documentation using OpenAPI standards. Available at: `/swagger-ui/index.html#/`


## Further Development
- A custom error can be returned for each data field to better direct the user right input
- SpringDoc OpenAPI can be made more useful by adding test data examples

## Other Resources

[▶️ Tutorial for learning SpringBoot](https://www.youtube.com/watch?v=-Fe0zk-F4OA)

[To Initialize the project](https://start.spring.io/)
