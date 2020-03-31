FROM openjdk:11
COPY target/auth-service-0.0.1-SNAPSHOT.jar /auth-service.jar
EXPOSE 8080
EXPOSE 9092
CMD ["java", "-jar", "-Dspring.profiles.active=dev", "/auth-service.jar"]