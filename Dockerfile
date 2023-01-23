FROM openjdk:19-jdk-alpine

COPY target/43scjNotifyApp-0.0.1-SNAPSHOT.jar 43scjNotifyApp.jar
ENTRYPOINT ["java","-jar","/43scjNotifyApp.jar"]

EXPOSE 8070