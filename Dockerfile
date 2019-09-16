FROM openjdk:8-alpine
# Avoid problems with gclibc arenas
ENV MALLOC_ARENA_MAX=4

RUN apk update && apk add bash

RUN mkdir -p /opt/app
ENV PROJECT_HOME /opt/app
WORKDIR $PROJECT_HOME

COPY target/twitter-test-0.0.1-SNAPSHOT.jar $PROJECT_HOME/twitter-test.jar
EXPOSE 8080

# RUN EXPORT JAVA_OPTS="-XX:PermSize=8G -XX:MaxPermSize=8G"
ENTRYPOINT ["java","-Dspring.data.mongodb.uri=mongodb://mongodb:27017/twitterdb","-jar","twitter-test.jar"]