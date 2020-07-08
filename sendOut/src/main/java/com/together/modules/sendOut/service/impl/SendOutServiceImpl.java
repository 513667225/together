package com.together.modules.sendOut.service.impl;

import com.together.entity.Spell;
import com.together.modules.sendOut.service.SendOutService;
import com.together.modules.sendOut.service.client.OrderServiceClient;
import com.together.util.P;
import com.together.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Agu
 */
@Component
public class SendOutServiceImpl implements SendOutService {

    @Autowired
    private  OrderServiceClient orderServiceClient;


    @Override
    public R createOrder(List<Spell> list) {
        for (Spell spell : list) {
            P p =new P();
            p.put("orderStatus",0);
            p.put("aftersaleStatus",0);
            p.put("addressId",spell.getAddress_id());
            p.put("goodsPrice",spell.getGoods_price());
            p.put("orderPrice",spell.getGoods_price());
            p.put("actualPrice",spell.getGoods_price());
            orderServiceClient.newOrder(p);
        }
        return null;
    }
}
