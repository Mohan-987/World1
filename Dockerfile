FROM openjdk:11
EXPOSE 8080
COPY target/world.jar world.jar
ENTRYPOINT ["java","-jar","/world.jar"]
