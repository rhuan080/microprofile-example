FROM maven:3.5.2

EXPOSE 9990 8080 8181

ENV PROJECT_HOME /etc/mp-subject
COPY ./target /etc/mp-subject/target

RUN mkdir -p /etc/mp-subject/config

WORKDIR ${PROJECT_HOME}

CMD ["java", "-jar","-Xdebug", "-Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5006", "target/mp-subject-1.0-SNAPSHOT-thorntail.jar"]