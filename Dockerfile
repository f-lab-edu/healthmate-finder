FROM openjdk:11

EXPOSE 8080

COPY build/libs/*.jar helparty.jar

ENTRYPOINT ["java", "--spring.config.location=classpath:file:/app/application-dev.yml", "-jar", "/helparty.jar"]
