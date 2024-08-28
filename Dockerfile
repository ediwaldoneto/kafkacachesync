FROM openjdk:17-jdk-alpine

COPY build/libs/salesapp.jar /app/salesapp.jar

WORKDIR /app

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "salesapp.jar"]
