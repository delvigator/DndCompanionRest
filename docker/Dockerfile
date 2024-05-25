FROM maven:2.7.7-openjdk-11 AS build

FROM openjdk:11

RUN apk update \
    && export TZ='Europa/Moscow'\
    && echo 'Europa/Moscow' > /etc/timezone \
    && rm -rf /var/cache/ \

RUN apk --update add nodejs npm
RUN npm i -g redoc-cli
COPY *.jar /app.jar

CMD ["sh","-c","java${JAVA_OPTS} -jar /app.jar ${@}"]