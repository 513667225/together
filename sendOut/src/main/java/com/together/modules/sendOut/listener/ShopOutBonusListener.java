package com.together.modules.sendOut.listener;

import com.together.entity.Spell;
import com.together.modules.sendOut.service.client.AdminServiceClient;
import com.together.parameter.MqParameter;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 省(市)代分成
 * @author Agu
 */
@Component
public class ShopOutBonusListener {


    @Autowired
    AdminServiceClient adminServiceClient;

    @RabbitListener(queues = MqParameter.SHARE_OUT_BONUS_QUEUE_NAME)
    public  void get(List<Spell> list){
        for (Spell spell : list) {
            adminServiceClient.updateAdmin(spell.getAdmin_id(),spell.getGoods_level().getProvinceManage());
        }

    }

}
