FROM openjdk:11
LABEL maintainer ="ihm_ima"
ADD target/ihm_IMA.jar ihm_IMA.jar
ENTRYPOINT ["java","-jar","ihm_IMA.jar"]

# docker build  -t service_ima:latest .
# -t : pour mettre un tag
# dot : pour désigner le repo actuel

# stop tout
# docker stop $(docker ps -a -q)