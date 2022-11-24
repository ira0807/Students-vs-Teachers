# Students vs Teachers

>A simple RESTful application that describes two entities: a teacher and a student, 
> with a relationship between them and the ability to perform CRUD operations on each entity, 
> that uses H2 in-memory database and supports Swagger to demonstrate and test all endpoints.

## ⚙ How to start using
>It is recommended to follow the following instructions to run:
1. Fork this repositories
2. Copy link of project
3. Open new project from Version Control and clone it
4. Run project

> HINT :
* Follow the link to view the database: 
  `http://localhost:8080/h2-console`
* Use username and password from `application.properties` file
* Follow the link to see all the information about the program provided by Swagger:
  `http://localhost:8080/swagger-ui/#/`

## 🧬 Project structure

- Controller - accept requests from clients and display information in browser
- Service - contains all the business logic of our application
- Repository - is responsible for communicating with the database

## 🎯 Features
- Create/delete/update a student
- Create/delete/update a teacher
- Add student to teacher
- Delete student from teacher
- Delete teacher from student
- Display all students/by first name/by last name/ by teacher
- Display all teachers/by first name/by last name/ by student

## 💻 Technologies
- Java 17
- Maven
- JPA/Hibernate
- Spring Boot
- H2 database
- Swagger
- Lombok
