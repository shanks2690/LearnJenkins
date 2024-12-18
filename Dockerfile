# Start with a lightweight base image that supports Java
FROM eclipse-temurin:17-jre

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file to the container
COPY target/LearnJenkins-0.0.1-SNAPSHOT.jar app.jar

# Expose the port your application will run on
EXPOSE 8081

# Command to run the application
CMD ["java", "-jar", "app.jar"]
