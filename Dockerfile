FROM openjdk:8-jre-alpine
RUN mkdir -p /app && \
    addgroup java && \
    adduser -D -G java -h /app -s /sbin/nologin java && \
    chown -R java:java /app
USER java
WORKDIR /app
COPY target/decathlon-test-app-*.jar /app/decathlon-test-app.jar
EXPOSE 8080/tcp
CMD ["java", "-jar", "decathlon-test-app.jar"]