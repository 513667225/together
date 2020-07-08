package com.together.modules.goods.init;

import com.alibaba.fastjson.JSON;
import com.together.modules.goods.entity.GoodsEntity;
import com.together.modules.goods.service.IGoodsService;
import com.together.modules.goods.service.impl.GoodsServiceImpl;
import com.together.util.P;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GoodshotInit implements InitializingBean {

    @Autowired
    ValueOperations valueOperations;

    @Autowired
    IGoodsService goodsService;


    @Override
    public void afterPropertiesSet() throws Exception {
        P p=new P();
        p.put("goods_nature",2);
        p.put("limit",5);
        List<GoodsEntity> goodsEntities = goodsService.queryLimitNature(p);
        valueOperations.set("goodsgroup",goodsEntities);
        p=new P();
        p.put("goods_nature",3);
        p.put("limit",5);
        List<GoodsEntity> goodsEntityList = goodsService.queryLimitNature(p);
        valueOperations.set("goodshot",goodsEntityList);
    }
}
