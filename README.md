# microservices-course

As self notes:

Udemy course: https://www.udemy.com/course/microservicios-con-spring-boot-y-spring-cloud

Summary of contents:

* Microservices creation with Spring Boot and spring-starters using Maven. 
    * For IntelliJ, remember using EditStarters plugin for convenience. After that you can modify the Spring starters using the "Generate..." action in pom.xml.
    * Using JPA with embedded default H2 database.
    * Basic architecture (controller, service, repository) concepts.
    * Load balancing using Ribbon library.
* Registry and discovery of services using Spring Eureka.
    * Register existing services in eureka, and communicate all of them.
    * Overview of Hystrix library for failure and latency tolerance.
    * Configuration and usage of Zuul as API gateway.
* Centralizing microservices configuration using Spring Cloud Config. Setup several configurations per environment and refresh on demand.
* CRUD APIs and client using Feign clients and RestTemplate.
* Extract and reuse common code in libraries, removing unnecessary dependencies.
* Implementing authentication and authorization through Spring Cloud security: using OAuth and JWT tokens.
    * JWT and OAuth 2 main features.
    * Spring Security basics.
    * Implementing OAuth2 filtering + protecting specific application resources.
* External relational database usage (MySQL, PostgreSQL)
* Implementing distributed logging using Sleuth + Zipkin server for statistics and analysis of logs.
    * Sleuth setup and logging features (grouping of requests, latency analysis)
    * Zipkin setup (server and microservices needed configuration)
    * Zipkin events persistence in relational database (MySQL)
    * Events queuing using RabbitMQ.
* Using docker for distributing and deploying microservices.
    * Usage of docker for creating images and containers of each microservices with Dockerfile.
    * Usage of existing docker containers for providing services (MySQL, RabbitMQ)