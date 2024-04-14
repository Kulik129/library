#FROM amazoncorretto:17
#COPY target/*.jar app.jar
#ENTRYPOINT ["java","-jar","/app.jar"]

FROM amazoncorretto:17
EXPOSE 8190
COPY target/issue-service-0.0.1-SNAPSHOT.jar .
ENTRYPOINT ["java", "-jar", "issue-service-0.0.1-SNAPSHOT.jar"]