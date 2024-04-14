#FROM amazoncorretto:17
#COPY target/*.jar app.jar
#ENTRYPOINT ["java","-jar","/app.jar"]

FROM amazoncorretto:17
EXPOSE 8765
COPY target/api-gateway-0.0.1-SNAPSHOT.jar .
ENTRYPOINT ["java", "-jar", "api-gateway-0.0.1-SNAPSHOT.jar"]