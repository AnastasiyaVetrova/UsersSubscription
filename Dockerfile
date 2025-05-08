FROM maven:3.9.5-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM openjdk:17-alpine
WORKDIR /app
RUN mkdir -p /logs
COPY --from=build /app/target/*.jar /app/app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]