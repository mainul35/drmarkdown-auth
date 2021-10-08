FROM adoptopenjdk/openjdk11
COPY target/auth-0.0.1-SNAPSHOT.jar auth.jar

ENTRYPOINT ["java", "-jar", "auth.jar"]

