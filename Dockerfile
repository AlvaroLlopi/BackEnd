FROM amazoncorretto:11-alpaine-jdk
MAINTAINER ALL
COPY target/ALL-0.0.1-SNAPSHOT.jar all-app.jar
ENTRYPOINT ["java","-jar","/all-app.jar"]