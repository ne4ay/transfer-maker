FROM openjdk:17
LABEL maintainer="Oleksii Nechaev"

ENV APP_HOME="/transfer-maker"
ENV JAR_FILE="transfer-maker-0.0.1.jar"

COPY ./target/$JAR_FILE app/$JAR_FILE
EXPOSE $APP_PORT

CMD ["java", "-jar", "app/transfer-maker-0.0.1.jar", "--server.port=8080"]