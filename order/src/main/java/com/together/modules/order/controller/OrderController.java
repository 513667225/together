package com.together.modules.order.controller;


import com.together.annotation.Pmap;
import com.together.modules.order.entity.OrderEntity;
import com.together.modules.order.service.IOrderService;
import com.together.modules.order.serviceClient.GoodsServiceClient;
import com.together.util.P;
import com.together.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

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
    public R getOrderPage(@Pmap P p)throws Exception{
        p.batchToInt("page","limit");
        return iOrderService.getOrderPage(p);
    }


    @RequestMapping("getOrderGoods")
    public R getOrderGoods(@Pmap P p) throws Exception {
        p.batchToInt("order_id");
        return iOrderService.queryOrderGoods(p);
    }

    @RequestMapping("getShopOrders")
    public R getShopOrders(@Pmap P p) throws Exception {
        p.batchToInt("shop_id");
        p.batchToInt("page","limit");
        return iOrderService.queryOrderByShopId(p);
    }

    /**
     * 修改
     * @param orderEntity
     * @return
     */

    @ResponseBody
    @RequestMapping("updateOrders")
    public R updateOrders(OrderEntity orderEntity){
        Date date = new Date();
        orderEntity.setShipTime(date);
        System.out.println(date);
        orderEntity.getShipSn("ship_sn");
        orderEntity.getShipChannel("ship_channel");
        return R.success("success",iOrderService.updateById(orderEntity));
    }

    /**
     * 增加
     * @param p
     * @return
     */
    public R insertOrders(@Pmap P p,OrderEntity orderEntity){

        return R.success("success",iOrderService.save(orderEntity));
    }
}
