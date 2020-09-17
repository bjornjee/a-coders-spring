FROM maven:3.6.3-jdk-11 as BUILD
COPY /src /app/src
COPY /pom.xml /app/pom.xml
WORKDIR /app
RUN mvn clean package

FROM openjdk:11
COPY --from=BUILD /app/target/a-coders-spring-app-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar","app.jar"]
