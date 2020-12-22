FROM openjdk:12
VOLUME /tmp
EXPOSE 8002
ADD ./target/springboot-item-service-0.0.1-SNAPSHOT.jar springboot-item-service.jar
ENTRYPOINT ["java", "-jar", "/springboot-item-service.jar"]

