FROM openjdk:11-jre-slim
COPY target/demo-0.0.1-SNAPSHOT.jar /app/app.jar
CMD ["java", "-jar", "/app/app.jar"]