FROM maven:3.5.2

USER root

EXPOSE 9990 8080 8181

ENV PROJECT_HOME /etc/mp-speaker

RUN echo ${JAEGER_SERVICE_NAME}

COPY ./target /etc/mp-speaker/target

RUN mkdir -p /etc/mp-speaker/config

WORKDIR ${PROJECT_HOME}

CMD ["java", "-jar",  "-Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5006", "target/mp-speaker-1.0-SNAPSHOT-thorntail.jar"]

