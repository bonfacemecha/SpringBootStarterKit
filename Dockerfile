# Use a Java 24 base image
FROM eclipse-temurin:24-jdk as build

# Set work directory
WORKDIR /app

# Copy project files
COPY . .

# Build the application
RUN ./mvnw clean package -DskipTests

# Final minimal image
FROM eclipse-temurin:24-jre

WORKDIR /app

# Copy JAR from the build stage
COPY --from=build /app/target/*.jar app.jar

# Run the app
ENTRYPOINT ["java", "-jar", "app.jar"]
