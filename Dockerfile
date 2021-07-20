FROM openjdk:11

EXPOSE 8080

COPY build/libs/*.jar helparty.jar
ADD /var/lib/deploy/config/application-dev.yml application-dev.yml

ENTRYPOINT ["java", "--spring.config.location=classpath:file:/application-dev.yml", "-jar", "/helparty.jar"]
