# Dockerfile para construir la imagen de la aplicación Spring Boot

FROM gradle:8.5-jdk21 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src

RUN gradle build -x test --no-daemon --max-workers=1 --parallel

FROM amazoncorretto:21-alpine-jdk
EXPOSE 8080
COPY --from=build /home/gradle/src/build/libs/*.jar app.jar

ENTRYPOINT ["java", "-Xmx512m", "-jar", "/app.jar"]