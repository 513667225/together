package com.together.modules.sendOut.listener;

import com.together.entity.Spell;
import com.together.modules.sendOut.service.client.ShopServiceClient;
import com.together.parameter.MqParameter;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 商户分成
 * @author Agu
 */
@Component
public class ShopShareListener {


    @Autowired
    private ShopServiceClient shopServiceClient;

    @RabbitListener(queues = MqParameter.SHOP_SHARE_QUEUE_NAME)
    public  void get(List<Spell> list){

        for (Spell spell : list) {
            shopServiceClient.shopShare(spell.getShopuser_id(),spell.getShop_capital());
        }

    }

}
