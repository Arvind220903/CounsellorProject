# Use Java 17
FROM eclipse-temurin:17-jdk-alpine

# Set working directory
WORKDIR /app

# Copy Maven files
COPY pom.xml .
COPY mvnw .
COPY .mvn .mvn

# Download dependencies
RUN ./mvnw dependency:go-offline

# Copy source code
COPY src src

# Build the app
RUN ./mvnw clean package -DskipTests

# Run the jar
EXPOSE 8080
CMD ["java", "-jar", "target/*.jar"]
