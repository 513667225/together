package com.together.config;

import com.together.parameter.MqParameter;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class MqConfig {


    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory("47.114.38.198", 5672);
        connectionFactory.setUsername("admin");
        connectionFactory.setPassword("admin");
        connectionFactory.setVirtualHost("/");
        connectionFactory.setPublisherConfirms(true);
        return connectionFactory;
    }

    @Bean
    public SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory(ConnectionFactory connectionFactory){
        SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory = new SimpleRabbitListenerContainerFactory();
        simpleRabbitListenerContainerFactory.setConnectionFactory(connectionFactory);
        simpleRabbitListenerContainerFactory.setBatchListener(true);
        simpleRabbitListenerContainerFactory.setConsumerBatchEnabled(true);
        simpleRabbitListenerContainerFactory.setBatchSize(100);
        simpleRabbitListenerContainerFactory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        return simpleRabbitListenerContainerFactory;
    }

    @Bean
    @Scope("prototype")
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        //注意  这个ConnectionFactory 是使用javaconfig方式配置连接的时候才需要传入的  如果是yml配置的连接的话是不需要的
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        return template;
    }


    @Bean
    public DirectExchange defaultExchange() {
        return new DirectExchange(MqParameter.SPELL_EXCHANGE_NAME);
    }

    @Bean
    public Queue queue() {
        //名字  是否持久化
        return new Queue(MqParameter.SPELL_QUEUE_NAME, true);
    }

    @Bean
    public Binding binding() {
        //绑定一个队列  to: 绑定到哪个交换机上面 with：绑定的路由建（routingKey）
        return BindingBuilder.bind(queue()).to(defaultExchange()).with(MqParameter.SPELL_EXCHANGE_KEY_NAME);
    }


    @Bean
    public DirectExchange loserExchange() {
        return new DirectExchange(MqParameter.LOSER_EXCHANGE_NAME);
    }

    @Bean
    public Queue loserQueue() {
        //名字  是否持久化
        return new Queue(MqParameter.LOSER_QUEUE_NAME, true);
    }

    @Bean
    public Binding loserBinding() {
        //绑定一个队列  to: 绑定到哪个交换机上面 with：绑定的路由建（routingKey）
        return BindingBuilder.bind(loserQueue()).to(loserExchange()).with(MqParameter.LOSER_EXCHANGE_KEY_NAME);
    }


    @Bean
    public DirectExchange createExchange() {
        return new DirectExchange(MqParameter.CREATE_GROUP_EXCHANGE_NAME);
    }

    @Bean
    public Queue createQueue() {
        //名字  是否持久化
        return new Queue(MqParameter.CREATE_GROUP_QUEUE_NAME, true);
    }

    @Bean
    public Binding createBinding() {
        //绑定一个队列  to: 绑定到哪个交换机上面 with：绑定的路由建（routingKey）
        return BindingBuilder.bind(createQueue()).to(createExchange()).with(MqParameter.CREATE_GROUP_EXCHANGE_KEY_NAME);
    }





}