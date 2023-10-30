FROM openjdk:8-jdk-alpine
EXPOSE 8089
ADD http://192.168.100.55:8081/repository/maven-releases/tn/esprit/spring/gestion-station-ski/1.0/gestion-station-ski-1.0.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]


