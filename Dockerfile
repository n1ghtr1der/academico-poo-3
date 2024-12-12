FROM maven:3.8.3-openjdk-17 AS build

WORKDIR /build

COPY . .

RUN mvn clean install -DskipTests

FROM openjdk:17-ea-20-slim

RUN groupadd -r spring

RUN useradd -r -g spring spring

WORKDIR /src

RUN chown -R spring:spring /src

COPY --from=build --chown=spring:spring /build/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT [ "java", "-jar", "/src/app.jar" ]