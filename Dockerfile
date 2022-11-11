FROM alpine
RUN mkdir -p /app
ADD ./target/*.jar /app