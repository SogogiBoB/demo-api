FROM openjdk:11

ADD build/libs/jhjo-0.0.1.jar jhjo-0.0.1.jar 

EXPOSE 2000

ENTRYPOINT ["java","-jar","jhjo-0.0.1.jar"]