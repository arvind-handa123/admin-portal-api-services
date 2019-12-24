From openjdk:8
MAINTAINER yabx.co
WORKDIR /var/lib/jenkins/workspace/admin-portal-pipeline/admin-portal/target/
EXPOSE 8080
EXPOSE 3306
EXPOSE 6379
COPY target/portal-1.0.jar portal.jar
ENTRYPOINT ["java","-jar", "portal.jar"]
CMD ["-start"]
