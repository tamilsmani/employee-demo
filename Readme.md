# Spring Boot Employee demo Example


## Requirements

1. Java - 1.8.x

2. Maven - 3.x.x

3. MySQL - 5.x.x

4. FlywayDB Plugin

5. Swagger UI & API

## Setup

**1. Clone the application**

```bash
https://github.com/tamilsmani/employee-demo.git
```

**2. Create Mysql database**
```bash
create database employee_demo
```

**3. Update mysql username and password as per your installation and it is externalize outside**

+ create application.properties anywhere in your disk and add below entries and update respective value.
**Note:** Sample file kept inside "employee-demo\src\main\resources\application.properties" for your reference 

```bash
database.hibernate.dialect=org.hibernate.dialect.MySQL5InnoDBDialect
database.driver.name=com.mysql.cj.jdbc.Driver
database.db.url=jdbc:mysql://localhost:3306/empolyee_demo
database.schema.user.name=root
database.scheam.password=root

employee.demo.username=admin
employee.demo.password=admin

```
**4. Now migrate DB using Flyway plugin**

```bash
cd employee-demo
mvn flyway:migrate -Ddb.user=root -Ddb.password=root -Ddb.url=jdbc:mysql://localhost:3306/employee_demo
```
**5. Package your project - Runs with test case**

```bash
mvn clean install -Dapp.home=c:/temp
```
**6. Run employee-demo application**

```bash
java -Dapp.home=C:/temp -Dserver.servlet.context-path=/employee-demo -Dspring.flyway.enabled=false -jar target/employee-demo-0.0.1-SNAPSHOT.jar
```

**7. Swagger API - Details**

```bash
http://localhost:8080/employee-demo/swagger-ui.html
```

**8. API - Details**


```bash
Create new employee

curl -X POST -H "Content-Type:application/json" -H "Authorization: Basic YWRtaW46YWRtaW4=" -d '{
  "firstName": "Tamil",
  "lastName": "Selvan",
  "gender": "MALE",
  "dateOfBirth": "06-06-1980",
  "department": {
    "code": "DPHY"
  }
}' http://localhost:8080/employee-demo/api/v1/employee/create
```

```bash
Load all employees order by first name

curl -v -s -H "Authorization: Basic YWRtaW46YWRtaW4=" http://localhost:8080/employee-demo/api/v1/employee/loadAll
```
