# Producer-Application
<h2>Producer Consumer Application which will use Rabbit MQ as a messaging queue.Producer will produce message which will be sent to 
rabbit mq and finally consumer application will consume the message<h2>

<h4>Steps to Run the application</h4>
1)Install Java and STS IDE<br>
2)Download/clone bith Producer-Application and Consumer Application in STS IDE.<br>
3)Right click and Maven Update->Force Update<br>
4)Install and Run Rabbit MQ--->>>>>brew install rabbitmq<br>
5)Run Producer and Consumer Application on different ports to see the result real time.<br>
<br>
Application and Connection Properties in Producer APP<br>

spring.rabbitmq.host=localhost<br>
spring.rabbitmq.port=5672<br>
spring.rabbitmq.username=guest<br>
spring.rabbitmq.password=guest<br>
jsa.rabbitmq.queue=direct-queue #Defining a sample queue<br>
jsa.rabbitmq.exchange=direct-exchange #defining a exchange direct exchange type<br>
jsa.rabbitmq.routingkey=direct #defining a routing key<br>
spring.main.allow-bean-definition-overriding=true<br>
<br>
<br>

Application and Connection Properties in Consumer APP<br>

server.port=9000<br>
spring.rabbitmq.host=localhost<br>
spring.rabbitmq.port=5672<br>
spring.rabbitmq.username=guest<br>
spring.rabbitmq.password=guest<br>
<br>


Screenshots Related to Project<br>
1)Fanout Exchange<br>
2)Topic Exchange<br>
3)Direct Exchange<br>

<img src="https://ibb.co/Swg77sQ" width="350">
<img src="https://ibb.co/SsHSrrF" width="350">
<img src="https://ibb.co/PW5M8Mj" width="350">





