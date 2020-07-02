package com.together.modules.spell.controller;


import com.together.annotation.Pmap;
import com.together.entity.GoodsEntity;
import com.together.modules.spell.serviceClient.GoodsServiceClient;
import com.together.modules.spell.serviceClient.UserServiceClient;
import com.together.util.P;
import com.together.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/spell")
public class SpellController {


    @Autowired
    GoodsServiceClient goodsServiceClient;

    @Autowired
    UserServiceClient userServiceClient;


    @RequestMapping("/together")
    public R together(@Pmap P p)throws Exception {

        Integer userId = p.getInt("userId");
        Integer goodsId = p.getInt("goodsId");
        R goodsResult = goodsServiceClient.queryGoodsById(goodsId);
        GoodsEntity goodsEntity = goodsResult.thisToEntity(GoodsEntity.class);
//        System.out.println(goodsResult.get("data"));
        R userResult = userServiceClient.getUserById(userId);

        return  R.success();
    }

}
