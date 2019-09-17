FROM node:latest

RUN mkdir -p /opt/app
ENV PROJECT_HOME /opt/app
WORKDIR $PROJECT_HOME

COPY . .
EXPOSE 3000

RUN npm i