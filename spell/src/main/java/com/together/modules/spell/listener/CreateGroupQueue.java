package com.together.modules.spell.listener;


import com.together.parameter.MqParameter;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CreateGroupQueue {


    @RabbitListener(queues = MqParameter.CREATE_GROUP_QUEUE_NAME,containerFactory = "simpleRabbitListenerContainerFactory")
    public  void get(List<String> message){
        System.out.println(message.size());
    }

}
