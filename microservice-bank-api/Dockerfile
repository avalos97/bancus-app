# syntax=docker/dockerfile:experimental
FROM openjdk:17-jdk as build
WORKDIR /app

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
RUN chmod +x mvnw
RUN sed -i 's/\r$//' mvnw
COPY src src
# RUN ./mvnw package -DskipTests
FROM openjdk:17-jdk
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]