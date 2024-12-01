# Use an official Java runtime as a parent image
FROM openjdk:23-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file to the container
COPY target/hw3-0.0.1-SNAPSHOT.jar hw3-0.0.1-SNAPSHOT.jar

# Expose the port the app runs on
EXPOSE 8080
 
# Run the application
ENTRYPOINT ["java", "-jar", "hw3-0.0.1-SNAPSHOT.jar"]
