# 1. Java 17 olan base image götürürük
FROM eclipse-temurin:17-jdk

# 2. Image'i kim hazırlayıb (metadata)
LABEL maintainer="payment-service"

# 3. Container daxilində iş qovluğunu təyin edirik
WORKDIR /app

# 4. Maven build-dən çıxmış JAR file-ini copy edirik, adını da app.jar qoyuruq
COPY target/ms-payment-0.0.1-SNAPSHOT.jar app.jar

# 5. Tətbiq 8088 portunda işləyir, onu expose edirik
EXPOSE 8089

# 6. Container başlayanda JAR-ı run edəcək komanda
ENTRYPOINT ["java", "-jar", "app.jar"]
