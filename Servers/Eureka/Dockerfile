FROM maven:3.6.1-jdk-8 as builder
WORKDIR /app
COPY . .
RUN mvn clean package
ENTRYPOINT ["sh", "-c", "mvn spring-boot:run"]
