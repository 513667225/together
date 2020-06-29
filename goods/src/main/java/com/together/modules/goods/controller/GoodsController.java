package com.together.modules.goods.controller;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.together.annotation.Pmap;
import com.together.modules.goods.entity.GoodsEntity;
import com.together.modules.goods.service.IGoodsService;
import com.together.util.Map2JavaBeanUtil;
import com.together.util.P;
import com.together.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商品基本信息表 前端控制器
 * </p>
 *
 * @author 
 * @since 2020-06-28
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    IGoodsService iGoodsService;
    @GetMapping("/getGoodsPage")
    public R getGoodsPage(@Pmap P p) throws Exception {
        System.out.println(p);
        p.put("shopId",1);
        return iGoodsService.queryGoodsByShopId(p);
    }


}
