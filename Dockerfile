FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY target/social-web-app-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
# Genom att använda sh -c garanterar vi att miljövariabler expanderas korrekt i containern
ENTRYPOINT ["sh", "-c", "java -jar app.jar"]
