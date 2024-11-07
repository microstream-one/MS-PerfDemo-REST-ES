FROM eclipse-temurin:17-ubi9-minimal

ENV RUN_USER=esra
ENV RUN_GROUP=esra

COPY target/pdesnode-0.1.jar server.jar

CMD ["java", "-XX:MaxRAMPercentage=60.0", "-XshowSettings:vm", "-jar", "server.jar"]