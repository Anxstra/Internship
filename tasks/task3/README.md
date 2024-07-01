# Задание #3 Docker – Date: 24 июня 2024 15:14
### To build docker image run  
```mvn clean package  -D docker.ignore=false```  
### Docker image will be outputted to  
*target\docker\repository*  
### to create and run docker container from image  
```docker run --rm repository/hello-world-image:1.0-SNAPSHOT```