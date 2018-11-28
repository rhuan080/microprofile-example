FROM maven:3.5.2

USER root

EXPOSE 9990 8080 8181

ENV PROJECT_HOME /etc/mp-speaker

COPY ./target /etc/mp-speaker/target

RUN mkdir -p /etc/mp-speaker/config

WORKDIR ${PROJECT_HOME}

CMD ["java", "-jar", "target/mp-speaker-1.0-SNAPSHOT-thorntail.jar"]

