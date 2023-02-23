FROM adoptopenjdk/openjdk8:alpine-jre
VOLUME /temp
EXPOSE 8889
ARG target/questionPro-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]