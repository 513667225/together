package com.together.modules.sendOut.listener;

import com.together.parameter.MqParameter;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 省(市)代分成
 * @author Agu
 */
@Component
public class ShopOutBonusListener {


    @RabbitListener(queues = MqParameter.SHARE_OUT_BONUS_QUEUE_NAME)
    public  void get(){

    }

}
