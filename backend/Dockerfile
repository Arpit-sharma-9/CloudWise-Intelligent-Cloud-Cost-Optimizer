# Dockerfile for CloudWise Backend (Spring Boot)
# Use a multi-stage build to reduce final image size

# Stage 1: Build the application
FROM maven:3.8.6-openjdk-17 AS build
WORKDIR /app

# Copy the pom.xml and download dependencies
COPY pom.xml ./
RUN mvn dependency:go-offline -B

# Copy the source code
COPY src ./src

# Build the application
RUN mvn package -DskipTests -B

# Stage 2: Run the application
FROM openjdk:17-jdk-slim
WORKDIR /app

# Copy the built JAR from the build stage
COPY --from=build /app/target/cloudwise-backend-*.jar app.jar

# Expose the port the app runs on
EXPOSE 8080

# Set environment variables for SQLite
ENV SPRING_DATASOURCE_URL=jdbc:sqlite:./cloudwise.db
ENV SPRING_DATASOURCE_DRIVER_CLASS_NAME=org.sqlite.JDBC
ENV SPRING_JPA_DATABASE_PLATFORM=org.hibernate.community.dialect.SQLiteDialect
ENV SPRING_JPA_HIBERNATE_DDL_AUTO=update

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
