package com.example.orderservice;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory.CacheMode;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;
import org.springframework.messaging.handler.annotation.support.MessageHandlerMethodFactory;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Enumeration;
import java.util.Map;
import java.util.Properties;


@Configuration
public class RabbitConfig implements RabbitListenerConfigurer {

    public static final String EXCHANGE_ORDER = "order-exchange";
    public static final String EXCHANGE_RETRY = "retry-exchange";
    public static final String EXCHANGE_ERROR = "error-exchange";
    public static final String RTK_TOPIC = "order*";
    public static final String RTK_RETRY_TOPIC = "retry*";
    public static final String RTK_ERROR_TOPIC = "error";

    @Value("${rmq.host}")
    private String rmqhost;
    @Value("${rmq.port}")
    private String rmqport;
    @Value("${rmq.username}")
    private String rmqusername;
    @Value("${rmq.password}")
    private String rmqpassword;
    @Value("${rmq.vhost}")
    private String rmqvhost;
    @Value("${rmq.protocol}")
    private String rmqprotocol;
    @Value("${rmq.order.queue.name}")
    private String orderQ;
    @Value("${rmq.retry.queue.name}")
    private String retryQ;
    @Value("${rmq.error.queue.name}")
    private String errorQ;
    @Value("${rmq.custom.consumer.count}")
    private String rmqCustomQconsumerCount;

    public String getOrderQ() {
        return orderQ;
    }

    public void setOrderQ(String orderQ) {
        this.orderQ = orderQ;
    }

    public String getRetryQ() {
        return retryQ;
    }

    public void setRetryQ(String retryQ) {
        this.retryQ = retryQ;
    }

    public String getErrorQ() {
        return errorQ;
    }

    public void setErrorQ(String errorQ) {
        this.errorQ = errorQ;
    }

    public String getRmqCustomQconsumerCount() {
        return rmqCustomQconsumerCount;
    }

    public void setRmqCustomQconsumerCount(String rmqCustomQconsumerCount) {
        this.rmqCustomQconsumerCount = rmqCustomQconsumerCount;
    }

    @Bean
    Queue autoOrderQueue() {
        return QueueBuilder.durable(orderQ).withArgument("x-dead-letter-exchange", EXCHANGE_RETRY).withArgument("x-dead-letter-routing-key", RTK_RETRY_TOPIC).build();
    }

    @Bean
    Queue autoRetryQueue() {
        return QueueBuilder.durable(retryQ).withArgument("x-message-ttl", 4000).withArgument("x-dead-letter-exchange", EXCHANGE_ORDER)
                .withArgument("x-dead-letter-routing-key", RTK_TOPIC)
                .build();
    }

    @Bean
    Queue autoErrorQueue() {
        return QueueBuilder.durable(errorQ).build();
    }

    @Bean
    Exchange autoOrderExchange() {
        return ExchangeBuilder.topicExchange(EXCHANGE_ORDER).build();
    }

    @Bean
    Exchange autoRetryExchange() {
        return ExchangeBuilder.topicExchange(EXCHANGE_RETRY).build();
    }

    @Bean
    Exchange autoErrorExchange() {
        return ExchangeBuilder.topicExchange(EXCHANGE_ERROR).build();
    }

    @Bean
    Binding binding(Queue autoOrderQueue, TopicExchange autoOrderExchange) {
        return BindingBuilder.bind(autoOrderQueue).to(autoOrderExchange).with(RTK_TOPIC);
    }

    @Bean
    Binding bindingretry(Queue autoRetryQueue, TopicExchange autoRetryExchange) {
        return BindingBuilder.bind(autoRetryQueue).to(autoRetryExchange).with(RTK_RETRY_TOPIC);
    }

    @Bean
    Binding bindingerror(Queue autoErrorQueue, TopicExchange autoErrorExchange) {
        return BindingBuilder.bind(autoErrorQueue).to(autoErrorExchange).with(RTK_ERROR_TOPIC);
    }

    @Bean
    org.springframework.amqp.rabbit.core.RabbitAdmin RabbitAdmin(final ConnectionFactory connectionFactory) {
        final org.springframework.amqp.rabbit.core.RabbitAdmin rabbitadmin = new org.springframework.amqp.rabbit.core.RabbitAdmin(connectionFactory);
        return rabbitadmin;
    }














    @Primary
    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
        try {

            final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
            rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());

            CachingConnectionFactory connectionFactory2 = (CachingConnectionFactory) rabbitTemplate.getConnectionFactory();

            System.out.println("cachemode:" + connectionFactory2.getCacheMode());
            System.out.println("Default close time out:" + CachingConnectionFactory.DEFAULT_CLOSE_TIMEOUT
                    + "channel cache size:" + connectionFactory2.getChannelCacheSize());
            Properties p = connectionFactory2.getCacheProperties();
            Enumeration<?> keys = p.keys();
            while (keys.hasMoreElements()) {
                String key = (String) keys.nextElement();
                String value = (String) p.get(key);
                System.out.println(key + ": " + value);
            }

            System.out.println("routingKey" + rabbitTemplate.getRoutingKey());
            return rabbitTemplate;

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Bean
    public ConnectionFactory connectionFactory() throws KeyManagementException, NoSuchAlgorithmException {

        com.rabbitmq.client.ConnectionFactory connectionFactory = new com.rabbitmq.client.ConnectionFactory();

        System.out.println(rmqhost);
        System.out.println(rmqport);
        connectionFactory.setHost(rmqhost);
        connectionFactory.setPort(Integer.parseInt(rmqport.trim()));
        connectionFactory.setUsername(rmqusername);
        connectionFactory.setPassword(rmqpassword);
        connectionFactory.setVirtualHost(rmqvhost);
        connectionFactory.setConnectionTimeout(0);

        if (rmqprotocol.trim().equalsIgnoreCase("amqps"))
            connectionFactory.useSslProtocol("TLSV1.2");


        System.out.println("connectiontimeout:" + connectionFactory.getConnectionTimeout());
        Map<String, Object> m1 = connectionFactory.getClientProperties();
        System.out.println("printing connection factory BasicClientProperties");
        for (String key : m1.keySet()) {
            System.out.println(key + " = " + m1.get(key));
        }

        CachingConnectionFactory cc = new CachingConnectionFactory(connectionFactory);
        cc.setChannelCacheSize(40);
        //cc.setCacheMode(CacheMode.CONNECTION);
        cc.setCacheMode(CacheMode.CHANNEL);
        // cc.setConnectionCacheSize(20);
        cc.setConnectionLimit(500);
        cc.setChannelCheckoutTimeout(1000L);

        cc.setPublisherConfirms(true);
        cc.setPublisherReturns(true);

        cc.setConnectionNameStrategy(f -> "arabbitmq-demo-publisher-subscriber");

        return cc;
    }


    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactoryCustom() throws KeyManagementException, NoSuchAlgorithmException {

        SimpleRabbitListenerContainerFactory listenerContainerFcatory = new SimpleRabbitListenerContainerFactory();

        System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ inside  rabbitListenerContainerFactory");
        listenerContainerFcatory.setConnectionFactory(connectionFactory());
        String s = this.rmqCustomQconsumerCount;

        int ccount = 0;
        if (null != s) ccount = Integer.valueOf(s);

        listenerContainerFcatory.setConcurrentConsumers(ccount);
        listenerContainerFcatory.setMaxConcurrentConsumers(10);
        listenerContainerFcatory.setPrefetchCount(4);
        listenerContainerFcatory.setAcknowledgeMode(AcknowledgeMode.MANUAL);

        return listenerContainerFcatory;
    }

    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    MessageHandlerMethodFactory messageHandlerMethodFactory() {
        DefaultMessageHandlerMethodFactory messageHandlerMethodFactory = new DefaultMessageHandlerMethodFactory();
        messageHandlerMethodFactory.setMessageConverter(consumerJackson2MessageConverter());
        return messageHandlerMethodFactory;
    }

    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar registrar) {
        registrar.setMessageHandlerMethodFactory(messageHandlerMethodFactory());
    }

    @Bean
    public MappingJackson2MessageConverter consumerJackson2MessageConverter() {
        return new MappingJackson2MessageConverter();
    }

}
