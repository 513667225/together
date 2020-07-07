package com.together.modules.sendOut.listener;


import com.together.parameter.MqParameter;
import com.together.parameter.SystemParameter;
import com.together.util.MqUtil;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SendOutListener {


    @Autowired
    SetOperations setOperations;



    @Autowired
    MqUtil mqUtil;
//
    @RabbitListener(queues = MqParameter.SPELL_QUEUE_NAME)
    public void get(String message) throws Exception{
//        setOperations.mo
        System.out.println(Integer.parseInt(message));
        //list:中奖的那3个人
        List list = setOperations.pop(message, SystemParameter.BINGO_NUMBER);
        //TODO:处理3人扣钱发货
        //商户分钱
        mqUtil.testSend(MqParameter.SHOP_SHARE_EXCHANGE_NAME,MqParameter.SHOP_SHARE_EXCHANGE_KEY_NAME,list);
        //世代省代分红
        mqUtil.testSend(MqParameter.SHARE_OUT_BONUS_EXCHANGE_NAME,MqParameter.SHARE_OUT_BONUS_EXCHANGE_KEY_NAME,list);
        //吧剩下的97人放到失败者队列进行结算或是后续操作
        mqUtil.testSend(MqParameter.LOSER_EXCHANGE_NAME,MqParameter.LOSER_QUEUE_NAME,message);
    }

}
