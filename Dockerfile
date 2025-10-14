FROM openjdk:17-alpine
ARG version=1.0-SNAPSHOT
ADD build/libs/resto-${version}.jar resto.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "resto.jar"]