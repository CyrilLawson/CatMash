FROM maven:3.6.3-openjdk-8
MAINTAINER Cyril LAWSON
WORKDIR .
RUN mkdir -p /usr/share/app
COPY ./* /usr/share/app/catmash
RUN cd /usr/share/app/catmash \
    && mvn clean package \
	&& cd /usr/share/app/catmash/target \
	&& cp catmash-*-SNAPSHOT.jar /app.jar
CMD ["-jar", "app.jar"]
ENTRYPOINT ["java"]
