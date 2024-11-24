
FROM gradle:8.2-jdk17 as build

WORKDIR /app

COPY . .

RUN mkdir -p /home/gradle/.gradle && chmod -R 777 /home/gradle/.gradle
RUN gradle build --no-daemon --refresh-dependencies





FROM openjdk:17-jdk-slim as package

WORKDIR /app

COPY --from=build /app/build/libs/product-service-*.jar product-service.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "product-service.jar"]