# Book Management System

## Overview
The Book Management System is a Spring Boot application designed to manage books, authors, and publishers. It provides RESTful APIs for creating, reading, updating, and deleting books, as well as searching for books based on various criteria.

## Technologies Used
- Java
- Spring Boot
- Maven
- Hibernate (JPA)
- Lombok
- Jakarta Validation
- Spring Security

## Getting Started

### Prerequisites
- Java 11 or higher
- Maven 3.6.0 or higher

### Installation
Clone the repository:
   ```sh
   git clone https://github.com/nguyenvanhao1209/book-management-system.git
   ```
## API Endpoints

### Auth Endpoints
- **Register a User**
    ```http
    POST /api/auth/register
    ```
- **Login**
    ```http
    POST /api/auth/login
    ```

### Book Endpoints
- **Create a Book**
  ```http
  POST /api/books/
  ```
- **Get All Books**
  ```http
    GET /api/books/
  ```
- **Search Books**
    ```http
    GET /api/books/?title={title}&author={author}&publisher={publisher}&startDate={startDate}&endDate={endDate}&direction={direction}&sortBy={sortBy}
    ```
- **Get Book by ID**
    ```http
        GET /api/books/{id}
    ```
- **Update a Book**
    ```http
        PUT /api/books/{id}
    ```
- **Delete a Book**
    ```http
        DELETE /api/books/{id}
    ```
### Publisher Endpoints

- **Create a Publisher**
    ```http
    POST /api/publishers/
    ```
- **Get All Publishers**
    ```http
    GET /api/publishers/
    ```
- **Get Publisher by ID**
    ```http
    GET /api/publishers/{id}
    ```
### User Endpoints

- **Get Current User**
    ```http
    GET /api/users/me
    ```
## Database Diagram
![Database Diagram](
    db.png
)