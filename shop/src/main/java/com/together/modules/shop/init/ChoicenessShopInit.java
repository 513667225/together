package com.together.modules.shop.init;

import com.together.entity.GoodsEntity;
import com.together.modules.shop.entity.ShopEntity;
import com.together.modules.shop.service.IShopService;
import com.together.util.P;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;

import java.util.List;

public class ChoicenessShopInit  implements InitializingBean {


    @Autowired
    ListOperations<String, ShopEntity> listOperations;

    @Autowired
    IShopService shopService;


    @Override
    public void afterPropertiesSet() throws Exception {
        P p=new P();
        p.put("limit",5);
        List<ShopEntity> shopEntities = shopService.queryLimitHotShop(p);
        for (ShopEntity shopEntity : shopEntities) {
            listOperations.leftPush("popularShops",shopEntity);
        }
    }
}
