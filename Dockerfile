# For Java 8, try this
FROM openjdk:17-jdk-slim

# cp staging/authorization.jar /opt/app/app.jar
COPY /target/authorization-0.0.1-SNAPSHOT.jar app/app.jar

# java -jar /opt/app/app.jar
ENTRYPOINT ["java","-jar","app/app.jar"]
