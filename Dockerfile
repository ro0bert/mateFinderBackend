#FROM openjdk:17-alpine
#COPY /build/libs/MateFinderBackend-0.0.1-SNAPSHOT.jar /usr/src/myapp
#WORKDIR /usr/src/myapp
#RUN javac Main.java
#CMD ["java", "Main"]



FROM openjdk:17-alpine
ADD /build/libs/MateFinderBackend-0.0.1-SNAPSHOT.jar MateFinderBackend-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["sh", "-c", "java -jar /MateFinderBackend-0.0.1-SNAPSHOT.jar"]
#ENV PROFILE=docker



#//TODO add profile with docker to local machine db
# // connection to db on



#CMD ["java", "-jar", "ateFinderBackend-0.0.1-SNAPS  HOT.jar"]