FROM maven:3.9.4-eclipse-temurin-21 AS build

WORKDIR /app

COPY backend /app/backend

RUN cd /app/backend && mvn clean package -DskipTests

FROM openjdk:21

WORKDIR /app

COPY --from=build /app/backend/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT [ "java", "-jar", "app.jar" ]