# divisor-java-springboot-mvc
The project is the implementation of the `FindDivisors` test assignment in Java based on Spring Boot using Spring MVC.

## Requirements
The project requires JDK 11 installed locally.

## Main tools/libraries used
* Java 11
* Spring Boot 2.6.0
* Swagger
* JUnit 5
* Mockito
* Spring Boot Test

## Design notes
### Structure
The project implementation follows some of the [Clean Architecture](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html) principles.
The base package `my.test.divisor.springboot.mvc` has 2 subpackages:
* `domain` which implements "core" functionality in the `DivisorService` service (in the `service` subpackage) 
and the required model classes (in the `model` subpackage)
* `web` which depends on `domain` and adapts for using as a REST service
It results in better modularization, easier testing and maintenance of the "core" functionality 
as well as easier possibilities to expose the "core" functionality with other interfaces (e.g. JSON API, gRPC, RSocket etc)

### Code generation
The `web` layer contains Spring `Controller`-s which extend `Api` interfaces (e.g. `my.test.divisor.springboot.mvc.web.api.FindDivisorsApi`).
The `Api` interfaces are generated during the Maven build based on the OpenApi service description (stored in 
`src/main/resources/openapi/DivisorAssignment.yaml`) with using the `org.openapitools:openapi-generator-maven-plugin`.
Using this "Contract First" approach ensures that no service consumer is not affected by following changes in the project.

## Tests
There are several levels of tests implemented in the project:
* Unit tests implemented as simple JUnit 5 tests which test "core" logic 
(see `my/test/divisor/springboot/mvc/domain/model/FilteringOptionTest.java` and 
`my/test/divisor/springboot/mvc/domain/service/DivisorServiceTest.java`)
* Controller tests implemented with `WebMvcTest` slice of Spring Boot Test which test REST interaction with the service
  (see `my/test/divisor/springboot/mvc/web/controller/FindDivisorsControllerTest.java`)
* Integration tests implemented using Spring Boot Test with the application started from the test and exposed on a random port
  (see `my/test/divisor/springboot/mvc/DivisorSpringbootMvcApplicationTests.java`)

## Build
The build is executed with Maven Wrapper.
To build the project the following command should be executed (from the project root):

(in Linux or Mac)
```shell
./mvnw clean package
```
(in Windows)
```shell
mvnw clean package
```
The application artifact can be found at `target/divisor-java-springboot-mvc-0.0.1-SNAPSHOT.jar`

To run the application locally (by default the `8080` port is used) the following command should be executed (from the project root):

(in Linux or Mac)
```shell
./mvnw spring-boot:run
```
(in Windows)
```shell
mvnw spring-boot:run
```
The application can be requested then bu the following url:
```shell
http://localhost:8080/findDivisors/20
```
A Docker image for the project (with the `latest` tag) is generated when the following command is executed:
(in Linux or Mac)
```shell
./mvnw install -Pdocker
```
(in Windows)
```shell
mvnw install -Pdocker
```
