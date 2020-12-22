FROM openjdk:12
VOLUME /tmp
ADD ./target/springboot-user-service-0.0.1-SNAPSHOT.jar user-service.jar
ENTRYPOINT ["java", "-jar", "/user-service.jar"]
