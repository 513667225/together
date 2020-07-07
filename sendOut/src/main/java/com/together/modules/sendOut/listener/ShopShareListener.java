package com.together.modules.sendOut.listener;

import com.together.parameter.MqParameter;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 商户分成
 * @author Agu
 */
@Component
public class ShopShareListener {


    @RabbitListener(queues = MqParameter.SHOP_SHARE_QUEUE_NAME)
    public  void get(){

    }

}
