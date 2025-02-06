FROM maven:3.9.4-eclipse-temurin-17 AS builder

WORKDIR /app

COPY ../commons /app/commons
WORKDIR /app/commons
RUN mvn clean install -DskipTests

COPY ./dash-service /app/dash-service
WORKDIR /app/dash-service
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jre

WORKDIR /app

COPY --from=builder /app/dash-service/target/dash-service*.jar app.jar

EXPOSE 8085

ENTRYPOINT ["java", "-jar", "app.jar"]