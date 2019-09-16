FROM openjdk:8-alpine

RUN apk update && apk add bash

RUN mkdir -p /opt/app
ENV PROJECT_HOME /opt/app
WORKDIR $PROJECT_HOME

COPY target/twitter-test-0.0.1-SNAPSHOT.jar $PROJECT_HOME/twitter-test.jar
EXPOSE 8080

ENTRYPOINT ["java","-Dspring.data.mongodb.uri=mongodb://localhost/twitterdb","-jar","twitter-test.jar"]