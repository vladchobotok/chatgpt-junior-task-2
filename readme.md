# Simple Inventory Management System

This is a Spring Boot application that provides a simple inventory management system for a store. It allows users to view a list of available products, add new products, and update existing ones. The application uses Hibernate to persist the product information in a MySQL database.

## Prerequisites

Before running the application, make sure you have the following prerequisites installed:

- Java Development Kit (JDK) 8 or later
- Apache Maven
- MySQL database

## Configuration

1. Open the `src/main/resources/application.properties` file.
2. Configure the MySQL database connection properties:
    - `spring.datasource.url`: the URL of the MySQL database
    - `spring.datasource.username`: the username to access the database
    - `spring.datasource.password`: the password to access the database

## Build and Run

1. Open a command prompt or terminal.
2. Navigate to the project directory.
3. Build the application using Maven:
```
   mvn clean package
```

4. Run the application:
```
   java -jar target/inventory-management-1.0.0.jar
   ```

The application will start running on `http://localhost:8080`.

## API Endpoints

- **GET /products**: Get a list of all products.
- **GET /products/{id}**: Get a product by its ID.
- **POST /products**: Create a new product.
- **PUT /products/{id}**: Update an existing product.

You can test the API endpoints using tools like Postman or cURL.

## Unit Tests

The application includes unit tests to ensure the correctness of its functionality. You can run the tests using Maven:
```
mvn test
```

## License

This project is licensed under the [MIT License](LICENSE).

## Feedback

**Was it easy to complete the task using AI?**

    Yes, that was quite easy. 

**How long did task take you to complete?**

    It took me around 1 hour.

**Was the code ready to run after generation? What did you have to change to make it usable?**

    All the code was ready to run after generation.

**Which challenges did you face during completion of the task?**

    I didn't face any challenges during completion of the task.

**Which specific prompts you learned as a good practice to complete the task?**

    For example, step-by-step, error handling, testing and documentation prompts. 