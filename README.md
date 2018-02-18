# Thermokrasia

Simple Java App to receive and register temperature measures grouped by samples in Mysql

How to start the Thermokrasia application
---

1. Run `mvn clean install` to build your application
2. Start application with `java -jar target/thermokrasia-1.0-SNAPSHOT.jar server config.yml`
3. To check that your application is running enter url `http://localhost:8080`
4. To register Temperatures, send POST to http://localhost:8080/temperature usin class Temperature as payload

Health Check
---

To see your applications health enter url `http://localhost:8081/healthcheck`
