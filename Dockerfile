FROM openjdk:11

EXPOSE 8080

COPY build/libs/*.jar helparty.jar

ENTRYPOINT ["java", "-jar", "/helparty.jar", "--spring.config.location=classpath:file:/app/application-dev.yml"]
