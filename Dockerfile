FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/spring-devops1-1.0.jar
ADD ${JAR_FILE} spring-devops1.jar
ENTRYPOINT ["java","-jar","/spring-devops1.jar"]