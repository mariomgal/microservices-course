FROM openjdk:12
VOLUME /tmp
EXPOSE 8888
ADD ./target/config-service-0.0.1-SNAPSHOT.jar config-service.jar
ADD ./src/main/resources/config config
ENTRYPOINT ["java", "-jar", "/config-service.jar"]

