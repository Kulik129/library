#FROM amazoncorretto:17
#COPY target/*.jar app.jar
#ENTRYPOINT ["java","-jar","/app.jar"]

FROM amazoncorretto:17
EXPOSE 8092
COPY target/google-books-service-0.0.1-SNAPSHOT.jar .
ENTRYPOINT ["java", "-jar", "google-books-service-0.0.1-SNAPSHOT.jar"]