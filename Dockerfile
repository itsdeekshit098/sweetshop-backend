# Stage 1: Build the JAR using Maven
FROM eclipse-temurin:17-jdk AS build
WORKDIR /app

COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline

COPY src ./src
RUN ./mvnw clean package -DskipTests

# Stage 2: Run the app
FROM eclipse-temurin:17-jdk
WORKDIR /app

# Copy the correct JAR from the build stage
COPY --from=build /app/target/sweetshop-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
