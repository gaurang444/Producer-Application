package com.gaurang.myproject;


import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import com.google.gson.Gson;

@RestController
public class HelloworldController {
	
	@Autowired
	private AmqpTemplate amqpTemplate;
	@Value("${jsa.rabbitmq.exchange}")
	private String exchange;
	
	@Value("${jsa.rabbitmq.routingkey}")
	private String routingKey;

	@GetMapping("/hello")
	public String hello(){
		return "Hello";
	}
	
    @PostMapping(path = "/fanout")
	public MainObject FanoutsendMessage(@RequestBody MainObject msg){
		
    	Gson gson = new Gson();
    	String jsonstr = gson.toJson(msg);

			amqpTemplate.convertAndSend("up-exchange","", jsonstr);
			System.out.println("mssagesent in fanout rabbit mq");

		return msg;
	}
    //one to one where message will be redirected to the queue with specific binding
    @PostMapping(path = "/direct")
	public MainObject DirectsendMessage(@RequestBody MainObject msg){
		
    	Gson gson = new Gson();
    	String jsonstr = gson.toJson(msg);

			amqpTemplate.convertAndSend(exchange,routingKey,jsonstr);
			System.out.println("mssage sent in direct exchange rabbit mq");

		return msg;
	}
    @PostMapping(path = "/topic")
	public MainObject TopicsendMessage(@RequestBody MainObject msg){
		
    	Gson gson = new Gson();
    	String jsonstr = gson.toJson(msg);

			amqpTemplate.convertAndSend("topic-exchange","up.#", jsonstr);
			System.out.println("mssage sent in topic exchange  rabbit mq");

		return msg;
	}
	


}