FROM maven:3.6.1-jdk-8
ARG EUREKA_IP=172.17.0.7
ENV EUREKA_HOSTED_IP=${EUREKA_IP}
WORKDIR /app
COPY . .
RUN mvn clean package
ENTRYPOINT ["sh", "-c", "mvn spring-boot:run"]
