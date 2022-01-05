# divisor-kotlin-springboot-mvc
The project is the implementation of the `FindDivisors` test assignment in Kotlin based on Spring Boot using Spring MVC.
It is very similar to the `divisor-java-springboot-mvc` but uses Kotlin instead of Java.
The projects show that Kotlin is very similar to Java and easily used for implementing services. 

## Requirements
The project requires JDK 11 installed locally.

## Main tools/libraries used
* Kotlin 1.6.10
* Spring Boot 2.6.2
* Swagger
* JUnit 5
* Mockk
* Spring Boot Test
* Gradle

## Design notes
### Structure
The project implementation follows some of the [Clean Architecture](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html) principles.
The base package `my.test.divisor.kotlin.springboot.mvc` has 2 subpackages:
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
(see `my/test/divisor/kotlin/springboot/mvc/domain/model/FilteringOptionTest.kt` and 
`my/test/divisor/kotlin/springboot/mvc/domain/service/DivisorServiceTest.kt`)
* Controller tests implemented with `WebMvcTest` slice of Spring Boot Test which test REST interaction with the service
  (see `my/test/divisor/kotlin/springboot/mvc/web/controller/FindDivisorsControllerTest.kt`)
* Integration tests implemented using Spring Boot Test with the application started from the test and exposed on a random port
  (see `my/test/divisor/kotlin/springboot/mvc/DivisorSpringbootMvcApplicationTests.kt`)

## Build
The build is executed with Gradle Wrapper.
To build the project the following command should be executed (from the project root):

(in Linux or Mac)
```shell
./gradlew clean build
```
(in Windows)
```shell
gradlew clean build
```
The application artifact can be found at `target/divisor-kotlin-springboot-mvc-0.0.1-SNAPSHOT.jar`

To run the application locally (by default the `8080` port is used) the following command should be executed (from the project root):

(in Linux or Mac)
```shell
./gradlew bootRun
```
(in Windows)
```shell
gradlew bootRun
```
The application can be requested then bu the following url:
```shell
http://localhost:8080/calculator/findDivisors/20
```
