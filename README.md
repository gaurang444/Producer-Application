# Producer-Application
<h1>I will Update the Proper Documentaion and Mysql table relationship and structure by today 2pm<h3>
<h3>Producer Consumer Application which will use Rabbit MQ as a messaging queue.Producer will produce message which will be sent to 
rabbit mq and finally consumer application will consume the message</h3><br>

<h4>Steps to Run the application</h4><br>
1)Install Java and STS IDE<br>
2)Download/clone bith Producer-Application and Consumer Application in STS IDE.<br>
3)Right click and Maven Update->Force Update Build the Project<br>
4)Install and Run Rabbit MQ--->>>>>brew install rabbitmq and brew services restart rabbitmq<br>
5)Run Producer and Consumer Application on different ports to see the result real time.<br>
<br>



Create  a DockerFile in Producer Application. with mvn clean install<br>
FROM openjdk:8<br>
ADD target/spring-boot-docker.jar spring-boot-docker.jar<br>
ENTRYPOINT ["java","-jar","spring-boot-docker.jar"]<br>


Run MongoDb,Mmysql,Rabbit each on sepeerate docker container.








Screenshots Related to Project<br>
1)Fanout Exchange<br>
2)Topic Exchange<br>
3)Direct Exchange<br>

<img src="https://ibb.co/Swg77sQ" width="350">
<img src="https://ibb.co/SsHSrrF" width="350">
<img src="https://ibb.co/PW5M8Mj" width="350">





