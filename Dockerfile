FROM gradle:8.5-jdk17 AS builder
WORKDIR /app
COPY backend/ ./backend/
COPY frontend/ ./frontend/

WORKDIR /app/backend
RUN gradle shadowJar

FROM openjdk:17-slim
WORKDIR /app

COPY frontend/ ./frontend/
COPY --from=builder /app/backend/build/libs/*.jar ./app.jar

CMD ["java", "-jar", "app.jar"]
