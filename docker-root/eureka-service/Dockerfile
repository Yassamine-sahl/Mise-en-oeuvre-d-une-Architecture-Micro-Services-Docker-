FROM openjdk:17-alpine
EXPOSE 8761
ARG JAR_FILE=adria-eureka-services-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]