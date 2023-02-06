# payment

Command for build without test cases = mvn clean install -Dmaven.test.skip

Command for build with test cases = mvn clean install

Run with docker and jib = mvn compile com.google.cloud.tools:jib-maven-plugin:2.5.0:build -Dimage=payment

Run with docker and jib (add plugin to pom) = mvn compile jib:build

Run with docker file (in project path) = docker build .

Run with java (cd target) = java -jar payment.jar
