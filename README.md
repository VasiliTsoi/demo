# Spring Boot MVC

Example of Spring MVC

### Setting up
- run sql/scripts.sql
- insert tes data to a db. Its data column needs to be updated prior import to mysql
- update spring.datasource parameters in application.properties
- run with maven then access it via http//:localhost:8080
```bash
mvn spring-boot:run
```
or

```bash
mvn clean package
java -jar target/demo.war
```