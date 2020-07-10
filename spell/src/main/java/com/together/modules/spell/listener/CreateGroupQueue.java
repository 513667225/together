package com.together.modules.spell.listener;


import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.Channel;
import com.together.entity.Spell;
import com.together.parameter.GroupQueueParameter;
import com.together.parameter.MqParameter;
import com.together.parameter.SystemParameter;
import com.together.util.MqUtil;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.BatchMessageListener;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

@Component
public class CreateGroupQueue  {


    @Autowired
    SetOperations zSetOperations;

    @Autowired
    ValueOperations valueOperations;

    @Autowired
    MqUtil mqUtil;
///


    @RabbitListener(queues = MqParameter.CREATE_GROUP_QUEUE_NAME,containerFactory = "simpleRabbitListenerContainerFactory")
    public  void get(List<Message> listMessage, Channel channel) throws Exception{

        int size = listMessage.size();
        long deliveryTag =listMessage.get(size -1).getMessageProperties().getDeliveryTag();
        Set<Spell> list = new HashSet<>();
        if (size !=100) {
            System.out.println("组团人数不够 size:"+ size);
            valueOperations.set(GroupQueueParameter.GROUP_QUEUE_NUMBER,size);
            channel.basicNack(deliveryTag,true,true);
        }else {
            for (Message message : listMessage) {
                System.out.println(zSetOperations.add(SystemParameter.i.toString(), JSON.parseObject(new String(message.getBody()), Spell.class)));
            }
            System.out.println("第"+SystemParameter.i.get()+"团组团成功");
            mqUtil.testSend(MqParameter.SPELL_EXCHANGE_NAME,MqParameter.SPELL_EXCHANGE_KEY_NAME,SystemParameter.i.get());
            SystemParameter.i.addAndGet(1);
            channel.basicAck(deliveryTag,true);
        }

    }

}
