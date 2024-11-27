FROM openjdk:17
ADD target/hw3-0.0.1-SNAPSHOT.jar hw3-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "hw3-0.0.1-SNAPSHOT.jar"]
