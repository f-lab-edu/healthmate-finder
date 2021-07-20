FROM openjdk:8-jdk-alpine

EXPOSE 8080

COPY build/libs/*.jar helparty.jar

ENTRYPOINT ["java", "-jar", "/helparty.jar"]
