#write instructions to generate Docker Image for accounts microservice

#start with a base image containing Java runtime
FROM openjdk:17-jdk-slim

#information around who maintains the image
LABEL maintainer="tommapcoder@gmail.com"

#set working directory
WORKDIR /app

#add the application's jar to the container
COPY target/accounts.jar app.jar

#execute the application
ENTRYPOINT ["java", "-jar", "app.jar"]