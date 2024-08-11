
# Bookish Shopcart

Bookish Shopcart is a Spring Boot application that provides a RESTful API for managing a collection of books. The application uses reactive programming with Project Reactor to handle asynchronous data streams.

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/3.3.2/maven-plugin)
* [Create an OCI image](https://docs.spring.io/spring-boot/3.3.2/maven-plugin/build-image.html)
* [Spring Reactive Web](https://docs.spring.io/spring-boot/docs/3.3.2/reference/htmlsingle/index.html#web.reactive)
* [Spring Data R2DBC](https://docs.spring.io/spring-boot/docs/3.3.2/reference/htmlsingle/index.html#data.sql.r2dbc)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a Reactive RESTful Web Service](https://spring.io/guides/gs/reactive-rest-service/)
* [Accessing data with R2DBC](https://spring.io/guides/gs/accessing-data-r2dbc/)

### Additional Links
These additional references should also help you:

* [R2DBC Homepage](https://r2dbc.io)

### Maven Parent overrides

Due to Maven's design, elements are inherited from the parent POM to the project POM.
While most of the inheritance is fine, it also inherits unwanted elements like `<license>` and `<developers>` from the parent.
To prevent this, the project POM contains empty overrides for these elements.
If you manually switch to a different parent and actually want the inheritance, you need to remove those overrides.


## Features

- Retrieve all books
- Retrieve a book by its ID

## Technologies Used

- Java
- Spring Boot
- Spring WebFlux
- Project Reactor
- H2 Database

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven

### Installation

1. Clone the repository:
    ```sh
    git clone https://github.com/yourusername/bookish-shopcart.git
    cd bookish-shopcart
    ```

2. Build the project:
    ```sh
    mvn clean install
    ```

3. Run the application:
    ```sh
    mvn spring-boot:run
    ```

### API Endpoints

- **GET /api/books**: Retrieve all books
- **GET /api/books/{id}**: Retrieve a book by its ID

### Example Requests

- Retrieve all books:
    ```sh
    curl -X GET http://localhost:8080/api/books
    ```

- Retrieve a book by its ID:
    ```sh
    curl -X GET http://localhost:8080/api/books/1
    ```
- add new book:
    ```sh
    curl -X POST http://localhost:8080/api/books
    ```
- update a book:
    ```sh
    curl -X PUT http://localhost:8080/api/books
    ```

## Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/your-feature`)
3. Commit your changes (`git commit -m 'Add some feature'`)
4. Push to the branch (`git push origin feature/your-feature`)
5. Open a pull request

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
