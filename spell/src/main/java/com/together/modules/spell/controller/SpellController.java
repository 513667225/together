package com.together.modules.spell.controller;


import com.together.annotation.Pmap;
import com.together.enun.TogetherNumber;
import com.together.modules.spell.service.SpellService;
import com.together.modules.spell.serviceClient.GoodsServiceClient;
import com.together.modules.spell.serviceClient.UserServiceClient;
import com.together.parameter.MqParameter;
import com.together.util.MqUtil;
import com.together.util.P;
import com.together.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/spell")
public class SpellController {


    @Autowired
    private SpellService spellService;

    @Autowired
    private GoodsServiceClient goodsServiceClient;

    @Autowired
    private UserServiceClient userServiceClient;


    @Autowired
    MqUtil mqUtil;

    @RequestMapping("/together")
    public R together(@Pmap P p) throws Exception {

        Integer userId = p.getInt("userId");
        Integer goodsId = p.getInt("goodsId");
//        R goodsResult = goodsServiceClient.queryGoodsById(goodsId);
//        GoodsEntity goodsEntity = goodsResult.thisToEntity(GoodsEntity.class);
////        System.out.println(goodsResult.get("data"));
//        R userResult = userServiceClient.getUserById(userId);
        spellService.startTogether(userId,goodsId, TogetherNumber.forNumber(1));
        return R.success();
    }


    @RequestMapping("/test")
    public R test(){
        mqUtil.testSend(MqParameter.CREATE_GROUP_EXCHANGE_NAME,MqParameter.CREATE_GROUP_EXCHANGE_KEY_NAME,"1");
        return R.success();

    }

}
