FROM openjdk:8

EXPOSE 8080

COPY build/libs/*.jar helparty.jar

ENTRYPOINT ["java", "-jar", "/helparty.jar"]
