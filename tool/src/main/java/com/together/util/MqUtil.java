package com.together.util;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * MQ发消息帮助类
 * @author  Agu
 */

@Component
public class MqUtil {

    @Autowired
    RabbitTemplate rabbitTemplate;

    public void testSend(String exchange,String routingKey,Object obj) {
        rabbitTemplate.convertAndSend(exchange, routingKey, obj);
    }

}
