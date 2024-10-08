FROM openjdk:17-jdk-alpine

RUN apk update && apk add bash

WORKDIR /app
COPY ./app/study-scala-app-1.0-SNAPSHOT /app
RUN chmod +x /app/bin/study-scala-app
CMD /app/bin/study-scala-app -Dplay.http.secret.key=${PLAY_SECRET} -Dconfig.file=/app/conf/application.beta.conf -Dlogger.file=/app/conf/logback-beta.xml
