FROM openjdk:8-jdk-alpine
EXPOSE 8089
ADD http://http://192.168.1.100:8081/1/repository/maven-releases/tn/esprit/spring/gestion-station-ski/1.0/gestion-station-ski-1.0.jar /app/gestion-station-ski.jar
ENTRYPOINT ["java","-jar","gestion-station-ski.jar"]