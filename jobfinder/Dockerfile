# Use a lightweight Java runtime image
FROM eclipse-temurin:17-jdk-alpine

# Set working directory in the container
WORKDIR /app

# Copy your built JAR file into the container
COPY target/*.jar app.jar

# Expose the port the Spring Boot app runs on
EXPOSE 8080

# Run the JAR file when the container starts
ENTRYPOINT ["java", "-jar", "app.jar"]