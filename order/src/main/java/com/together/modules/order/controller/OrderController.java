package com.together.modules.order.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.together.annotation.Pmap;
import com.together.modules.order.entity.OrderEntity;
import com.together.modules.order.mapper.OrderMapper;
import com.together.modules.order.service.IOrderService;
import com.together.modules.order.serviceClient.GoodsServiceClient;
import com.together.util.P;
import com.together.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 订单表 前端控制器
 * </p>
 *
 * @author 
 * @since 2020-06-28
 */
@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private IOrderService iOrderService;


    @Autowired
    private GoodsServiceClient goodsServiceClient;

    //分页查询
    @RequestMapping("getOrderPage")
    public R getOrderPage(@Pmap P p){
        p.batchToInt("page","limit");
        return iOrderService.getOrderPage(p);
    }


    @RequestMapping("getOrderGoods")
    public R getOrderGoods(@Pmap P p){
        p.batchToInt("order_id");
        return iOrderService.queryOrderGoods(p);
    }

    @RequestMapping("getShopOrders")
    public R getShopOrders(@Pmap P p){
        p.batchToInt("shop_id");
        p.batchToInt("page","limit");
        return iOrderService.queryOrderByShopId(p);

    }
}
