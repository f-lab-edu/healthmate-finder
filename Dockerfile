FROM openjdk:8

EXPOSE 8080

COPY build/libs/*.jar helparty.jar

ENTRYPOINT ["java", "-jar", "-Dspring.config.location=/var/lib/deploy/config/application-dev.yml", "/helparty.jar"]
