package com.together.config;


import com.together.parameter.MqParameter;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
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
        //我这里直接在构造方法传入了
        //        connectionFactory.setHost();
        //        connectionFactory.setPort();
        connectionFactory.setUsername("admin");
        connectionFactory.setPassword("admin");
        connectionFactory.setVirtualHost("/");
        connectionFactory.setPublisherConfirms(true);
        return connectionFactory;
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
        return new DirectExchange(MqParameter.WAIT_EXCHANGE_NAME);
    }

    @Bean
    public Queue queue() {
        //名字  是否持久化
        return new Queue(MqParameter.WAIT_QUEUE_NAME, true);
    }

    @Bean
    public Binding binding() {
        //绑定一个队列  to: 绑定到哪个交换机上面 with：绑定的路由建（routingKey）
        return BindingBuilder.bind(queue()).to(defaultExchange()).with(MqParameter.WAIT_EXCHANGE_KEY_NAME);
    }


    @Bean
    public DirectExchange shareExchange() {
        return new DirectExchange(MqParameter.SHARE_OUT_BONUS_EXCHANGE_NAME);
    }

    @Bean
    public Queue shareQueue() {
        //名字  是否持久化
        return new Queue(MqParameter.SHARE_OUT_BONUS_QUEUE_NAME, true);
    }

    @Bean
    public Binding shareBinding() {
        //绑定一个队列  to: 绑定到哪个交换机上面 with：绑定的路由建（routingKey）
        return BindingBuilder.bind(shareQueue()).to(shareExchange()).with(MqParameter.SHARE_OUT_BONUS_EXCHANGE_KEY_NAME);
    }



    @Bean
    public DirectExchange shoutExchange() {
        return new DirectExchange(MqParameter.SHOP_SHARE_EXCHANGE_NAME);
    }

    @Bean
    public Queue shoutQueue() {
        //名字  是否持久化
        return new Queue(MqParameter.SHOP_SHARE_QUEUE_NAME, true);
    }

    @Bean
    public Binding shoutBinding() {
        //绑定一个队列  to: 绑定到哪个交换机上面 with：绑定的路由建（routingKey）
        return BindingBuilder.bind(shoutQueue()).to(shoutExchange()).with(MqParameter.SHOP_SHARE_EXCHANGE_KEY_NAME);
    }

}
