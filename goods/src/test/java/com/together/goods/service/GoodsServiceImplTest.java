package com.together.goods.service;

import com.together.AppGoods;
import com.together.modules.goods.entity.GoodsEntity;
import com.together.modules.goods.service.IGoodsService;
import com.together.util.P;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={AppGoods.class})// 指定启动类
public class GoodsServiceImplTest {
    @Autowired
    IGoodsService iGoodsService;

    @Test
    public void queryAllGoods() throws Exception {
        P p = new P();
        p.put("goods_name","1111111111");
       // p.put("category_id",1008001);
        p.put("rowIndex",0);
        p.put("limit",5);
        System.out.println(iGoodsService.queryAllGoods(p));
    }


    @Test
    public void queryLimitNature() throws Exception {
        P p = new P();
        p.put("goods_nature",3);
        p.put("limit",5);
        List<GoodsEntity> goodsEntities=iGoodsService.queryLimitNature(p);
        System.out.println(goodsEntities);
    }


}
