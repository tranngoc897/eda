FROM java:8-jre
MAINTAINER Tran Ngoc

ADD ./target/au-service.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/au-service.jar"]

EXPOSE 5003