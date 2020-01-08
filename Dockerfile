FROM ubuntu:latest
RUN apt-get -y update && apt-get -y upgrade
RUN apt-get -y install wget
RUN apt-get -y install openjdk-8-jdk wget


RUN wget http://apachemirror.wuchna.com/tomcat/tomcat-8/v8.5.50/bin/apache-tomcat-8.5.50.tar.gz

RUN tar xvfz apache-tomcat-8.5.50.tar.gz

ADD tasks-webapp.war /apache-tomcat-8.5.50/webapps


EXPOSE 8080
CMD /apache-tomcat-8.5.50/bin/catalina.sh run
