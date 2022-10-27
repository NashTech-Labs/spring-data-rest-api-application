## spring-data-REST-and-Jpa-example
* This project depicts the Spring Boot Example with Spring Data REST and JPA Example

## Description
This Project shows the list of Users which are stored in the In-Memory MySql Database. Using the following endpoints, different operations can be achieved:
- `/users` - This returns the list of Users in the Users table which is created in H2
- `/users/name/{name}` - This returns the details of the Users passed in URL
- `/users/load` - Add new users using the Users model. eg. { "name": "Ajay", "teamName": "Development", "salary": 100 }

## Libraries used
- Spring Boot
- Spring Configuration
- Spring REST Controller
- Spring JPA
- MySql
- Development Tools

## Git 2.10.0
- IntelliJ IDEA 2022.2.4

## Compilation Command
- `mvn clean install` - Plain maven clean and install