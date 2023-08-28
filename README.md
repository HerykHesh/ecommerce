# Xcale Challenger: E-Commerce
#### by Heryk Willians Simões

## Stack and dependencies:
- Development Language: [Java 11](https://openjdk.java.net)
- Main Framework: [Spring Boot Framework](https://spring.io/projects/spring-boot) 
- Database: [H2 Database](https://www.h2database.com/)
- Project Management: [Maven](https://maven.apache.org)
- Event-driven: [Axon](https://www.axoniq.io/)
- Java library: [Lombok](https://projectlombok.org/) 
- Api documentation: [Swagger](https://swagger.io/)
- Testing: [Junit Jupiter](https://junit.org/)

## Development tools
- [AXON Initializer](https://start.axoniq.io/)

## DataBase Config
To access the database, the developer needs to start the project and then click the link below. 
- [H2 Access](http://localhost:8080/h2-console)

When accessing the link, you will need to add the following information:

JDBC URL: jdbc:h2:mem:ecdb
username: sa
Password: password

Click the 'Connect' button.


## Axion Server access
To access axion, the developer needs to start Docker. Right-click on the 'Docker-compose: docker' option or type the following line in the terminal:
- docker-compose -f /src/main/docker/docker-compose-axonserver-se.yml up

- [Axion Server](http://localhost:8024/)


## Workflow:

#### 1. To initiate the process, the user should create a cart using the addCart POST request.
#### 2. Once the cart is created, the user can:
- Retrieve cart data using the getCart GET request.
- Add a product using the addProduct POST request.
- Delete a product using the deleteProduct DELETE request.

#### 3. All carts will automatically expire after 10 minutes, and they will be deleted accordingly.

## API documentation
[E-Commerce Api](http://localhost:8080/swagger-ui.html)

## Unit Testing:
- RUNNING mvn test
![test capture](misc/images/test1.jpg)
![test capture](misc/images/test2.jpg)

- RUNNING mvn surefire-report:report
![test capture](misc/images/test-report.jpg)

## API Documentation:
URL: {{service-url}}//swagger-ui/
![test capture](misc/images/swagger.jpg)





