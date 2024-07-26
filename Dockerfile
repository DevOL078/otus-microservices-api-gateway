FROM openjdk:17-jdk-alpine
ARG JAR_FILE="target/api-gateway-0.0.1-SNAPSHOT.jar"
WORKDIR /opt/app
COPY ${JAR_FILE} api-gateway.jar
ENTRYPOINT ["java", "-jar", "api-gateway.jar"]
EXPOSE 8000