FROM eclipse-temurin:26

WORKDIR /app

COPY target/SportMockAPI-1.0-SNAPSHOT-jar-with-dependencies.jar SMD-Console.jar

CMD ["java" ,"-jar" ,"SMD-Console.jar"]