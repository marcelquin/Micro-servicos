FROM openjdk:17-alpine3.14
ARG JAR_FILE=out/artifacts/test_jar/*.jar
COPY ./target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
EXPOSE 8080:8080