FROM adoptopenjdk/openjdk11
VOLUME /tmp
EXPOSE 8080
ADD target/payment.jar payment.jar
ENTRYPOINT exec java -jar /payment.jar payment


# mvn clean package
# docker build .