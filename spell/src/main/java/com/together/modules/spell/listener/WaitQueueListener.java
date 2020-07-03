package com.together.modules.spell.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Component;

@Component
public class WaitQueueListener {

    @Autowired
    SetOperations setOperations;


//    @RabbitListener(queues = MqParameter.WAIT_QUEUE_NAME)
//    public void get(String message) throws Exception{
//
//
//
//    }

}
