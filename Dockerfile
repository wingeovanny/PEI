# docker build -f Dockerfile -t pei .
# docker run -t -i --rm pei -p 8080:8080 
FROM openjdk:8-jdk-alpine
COPY "./target/challenge-1.0.jar" "app.jar"
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]