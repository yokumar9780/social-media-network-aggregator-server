FROM eclipse-temurin:21 AS builder
WORKDIR /workspace
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} network-aggregator-server.jar
RUN java -Djarmode=layertools -jar network-aggregator-server.jar extract

FROM gcr.io/distroless/java21-debian12:latest
WORKDIR /workspace

COPY --from=builder workspace/dependencies/ ./
COPY --from=builder workspace/spring-boot-loader/ ./
COPY --from=builder workspace/snapshot-dependencies/ ./
COPY --from=builder workspace/application/ ./

LABEL org.opencontainers.image.source=https://github.com/yokumar9780/social-media-network-aggregator-server
LABEL org.opencontainers.image.description="network-aggregator-server"

ENTRYPOINT ["java", "org.springframework.boot.loader.launch.JarLauncher"]
