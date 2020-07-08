package com.together.modules.shop.init;

import com.alibaba.fastjson.JSON;
import com.together.modules.shop.entity.ShopEntity;
import com.together.modules.shop.service.IShopService;
import com.together.util.P;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class ChoicenessShopInit  implements InitializingBean {


//    @Autowired
//    ListOperations<String, Object> listOperations;

    @Autowired
    ValueOperations<String, Object> valueOperations;

    @Autowired
    IShopService shopService;


    @Override
    public void afterPropertiesSet(){
        P p=new P();
        p.put("limit",5);
        List<ShopEntity> shopEntities = shopService.queryLimitHotShop(p);
        valueOperations.set("popularShops",shopEntities);
//        for (ShopEntity shopEntity : shopEntities) {
//            String s = JSON.toJSONString(shopEntity);
//            System.out.println(s);
//            listOperations.rightPush("popularShops",s);
//            listOperations.leftPush("popularShops",s);
//        }
    }
}
