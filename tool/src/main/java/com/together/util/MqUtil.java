package com.together.util;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MqUtil {

    @Autowired
    RabbitTemplate rabbitTemplate;

    public void testSend(String exchange,String routingKey,Object obj) {
        //至于为什么调用这个API 后面会解释
        //参数介绍： 交换机名字，路由建， 消息内容
        rabbitTemplate.convertAndSend(exchange, routingKey, obj);
    }

}
