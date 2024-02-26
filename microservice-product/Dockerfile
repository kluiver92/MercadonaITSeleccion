FROM adoptopenjdk/openjdk11:alpine-jre
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} microserviceproduct-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/microserviceproduct-0.0.1-SNAPSHOT.jar"]