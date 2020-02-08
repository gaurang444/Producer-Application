package com.gaurang.myproject;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
	@Value("${jsa.rabbitmq.queue}")
	private String queueName;
	
	@Value("${jsa.rabbitmq.exchange}")
	private String exchange;
	
	@Value("${jsa.rabbitmq.routingkey}")
	private String routingKey;
	
	@Bean
	Queue queue() {
		return new Queue(queueName, false);
	}
	@Bean
	Queue queue1() {
		return new Queue("queue1", false);
	}
	@Bean
	Queue queue2() {
		return new Queue("queue2", false);
	}
	
	@Bean
	FanoutExchange fanoutexchange() {
		return new FanoutExchange("up-exchange");
	}
	
	@Bean
	DirectExchange exchange() {
		return new DirectExchange(exchange);
	}
	@Bean
	TopicExchange topicexchange() {
		return new TopicExchange("topic-exchange");
	}
	@Bean
	Binding topicqueue2Binding(Queue queue2, TopicExchange topicexchange) {
		return BindingBuilder.bind(queue2).to(topicexchange).with("upgrad.routing");
	}

	@Bean
	Binding topicqueue1binding(Queue queue1, TopicExchange topicexchange) {
		return BindingBuilder.bind(queue1).to(topicexchange).with("up.#");
	}
	

	@Bean
	Binding queue1Binding(Queue queue1, FanoutExchange fanoutexchange) {
		return BindingBuilder.bind(queue1).to(fanoutexchange);
	}

	@Bean
	Binding queue2Binding(Queue queue2, FanoutExchange fanoutexchange) {
		return BindingBuilder.bind(queue2).to(fanoutexchange);
	}

	@Bean
	Binding fanoutbinding(Queue queue, FanoutExchange fanoutexchange) {
		return BindingBuilder.bind(queue).to(fanoutexchange);
	}
	@Bean
	Binding binding(Queue queue, DirectExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(routingKey);
	}
	@Bean
	public MessageConverter jsonMessageConverter() {
		return new Jackson2JsonMessageConverter();
	}

	
	@Bean
	public AmqpTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
		final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(jsonMessageConverter());
		return rabbitTemplate;
	}

}

