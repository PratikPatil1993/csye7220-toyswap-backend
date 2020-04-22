FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/toyswapRegister-1.jar
COPY ${JAR_FILE} registerapp.jar
ENTRYPOINT ["java","-jar","registerapp.jar"]
