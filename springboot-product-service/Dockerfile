FROM openjdk:12
VOLUME /tmp
ADD ./target/springboot-product-service-0.0.1-SNAPSHOT.jar product-service.jar
ENTRYPOINT ["java", "-jar", "/product-service.jar"]

