package com.example.club;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitMqConfig {

    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    /*
    public AmqpTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jackson2JsonMessageConverter());
        return rabbitTemplate;
    }*/


    @Bean
    public Queue outgoingQueue() {

        Map<String, Object> args = new HashMap<String, Object>();
        // The default exchange
        args.put("x-dead-letter-exchange", "");
        // Route to the incoming queue when the TTL occurs
        args.put("x-dead-letter-routing-key", "INCOMING_QUEUE");
        // TTL 5 seconds
        args.put("x-message-ttl", 5000);
        return new Queue("OUTGOING_QUEUE", false, false, false, args);
    }

    @Bean
    public Queue incomingQueue() {
        return new Queue("INCOMING_QUEUE");
    }

    ////////////////
    @Bean
    DirectExchange deadLetterExchange() {
        return new DirectExchange("deadLetterExchange");
    }


    @Bean
    Queue dlq() {
        return QueueBuilder.durable("deadLetterQueue").build();
    }

    @Bean
    Queue queue() {
        return QueueBuilder
                .durable("javainuse.queue")
                .withArgument("x-dead-letter-exchange", "deadLetterExchange")
                .withArgument("x-dead-letter-routing-key", "deadLetter")
                .build();
    }

    @Bean
    DirectExchange exchange() {
        return new DirectExchange("javainuse-direct-exchange");
    }


    @Bean
    Binding DLQbinding(Queue dlq, DirectExchange deadLetterExchange) {
        return BindingBuilder
                .bind(dlq)
                .to(deadLetterExchange)
                .with("deadLetter");
    }

    @Bean
    Binding binding(Queue queue, DirectExchange exchange) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with("javainuse");
    }


}