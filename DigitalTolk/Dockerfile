# Final image (no Maven, just runs the app)
FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY target/DigitalTolk-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8090
ENTRYPOINT ["java", "-jar", "app.jar"]
