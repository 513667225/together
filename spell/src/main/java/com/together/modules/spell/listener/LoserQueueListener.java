package com.together.modules.spell.listener;


import com.together.entity.Spell;
import com.together.enun.TogetherNumber;
import com.together.parameter.MqParameter;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class LoserQueueListener {


    @Autowired
    SetOperations setOperations;


    @RabbitListener(queues = MqParameter.LOSER_QUEUE_NAME)
    public void  get(String message){
        //拼团失败的97人
        Set<Spell> members = setOperations.members(message);
        //遍历
        for (Spell member : members) {
//          TODO  member.getUserId() 给这个用户返钱

            //拼团的次数+1
            member.setGameCount(member.getGameCount()+1);
            Integer gameCount = member.getGameCount();
            if (member.getTogetherNumber()!= TogetherNumber.ONCE){
                if (gameCount < member.getTogetherNumber().getNumber() ){

                }
            }
        }

    }
}
