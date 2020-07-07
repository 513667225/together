package com.together.modules.reMoney.listener;


import com.alibaba.fastjson.JSON;
import com.together.entity.Spell;
import com.together.enun.TogetherNumber;
import com.together.modules.reMoney.service.ReMoneyService;
import com.together.parameter.MqParameter;
import com.together.util.MqUtil;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class LoserQueueListener {


    @Autowired
    ReMoneyService reMoneyService;

    @Autowired
    SetOperations setOperations;

    @Autowired
    MqUtil mqUtil;


    @RabbitListener(queues = MqParameter.LOSER_QUEUE_NAME)
    public void  get(String message){
        //拼团失败的97人
        Set<Spell> members = setOperations.members(message);
        //遍历
        for (Spell member : members) {
//          TODO  member.getUserId() 给这个用户返钱
            reMoneyService.reMoney(member.getUser_id(),member.getGoods_level());
            //拼团的次数+1
            member.setGame_count(member.getGame_count()+1);
            Integer gameCount = member.getGame_count();
            if (member.getTogether_number()!= TogetherNumber.ONCE){
                if (gameCount < member.getTogether_number().getNumber() ){
                    //TODO 解决填充问题
                    mqUtil.testSend(MqParameter.CREATE_GROUP_EXCHANGE_NAME, MqParameter.CREATE_GROUP_EXCHANGE_KEY_NAME, JSON.toJSONString(member));
                }
            }
        }

    }
}
