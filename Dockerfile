From openjdk:8
MAINTAINER yabx.co
WORKDIR /var/lib/jenkins/workspace/kyc-pipeline/kyc/target/
EXPOSE 8080
EXPOSE 3306
COPY target/kyc-1.0.jar app.jar
ENTRYPOINT ["java","-jar", "app.jar"]
CMD ["-start"]
