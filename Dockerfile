FROM openjdk:8-jdk-alpine
EXPOSE 8089
#ADD target/gestion-station-ski-1.0.jar gestion-station-ski.jar
ADD http://192.168.56.156:8081/repository/maven-releases/tn/esprit/spring/gestion-station-ski/1.0/gestion-station-ski-1.0.jar gestion-station-ski.jar
ENTRYPOINT ["java","-jar","gestion-station-ski.jar"]