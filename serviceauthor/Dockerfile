FROM fabric8/java-alpine-openjdk8-jdk

USER root

EXPOSE 9990 8080 8181

ENV PROJECT_HOME /etc/app

COPY ./target /etc/app/target

RUN adduser -G root --no-create-home --disabled-password 1001 \
  && chown -R 1001 ${PROJECT_HOME} \
  && chmod -R "g+rwX" ${PROJECT_HOME} \
  && chown -R 1001:root ${PROJECT_HOME}
USER 1001

WORKDIR ${PROJECT_HOME}

CMD ["java", "-jar", "-Djboss.bind.address=0.0.0.0", "target/serviceauthor-wildfly.jar"]
