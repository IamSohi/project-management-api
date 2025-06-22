FROM openjdk:21-jdk-slim

WORKDIR /app

COPY target/project-management-api.jar /app/project-management-api.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/project-management-api.jar"]