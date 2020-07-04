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

    /**
     * 根据订单id查询商品
     * @param p
     * @return
     */
    @RequestMapping("getOrderGoods")
    public R getOrderGoods(@Pmap P p) throws Exception {
        p.batchToInt("order_id");
        return iOrderService.queryOrderGoods(p);
    }

    /**
     * 根据店铺id查询订单
     * @param p
     * @return
     */
    @RequestMapping("getShopOrders")
    public R getShopOrders(@Pmap P p) throws Exception {
        p.batchToInt("shop_id");
        p.batchToInt("page","limit");
        return iOrderService.queryOrderByShopId(p);
    }


    /**
     * 根据订单ID查询订单
     * @param p
     * @return
     */
    @RequestMapping("/queryOrdersById")
    public R queryOrdersById(@Pmap P p){
        OrderEntity orderId = iOrderService.getById(p.getInt("order_id"));
        return R.success("操作成功",orderId);
    }

    /**
     * 修改订单
     * @param p
     * @return
     */

    @ResponseBody
    @RequestMapping("updateOrders")
    public R updateOrders(@Pmap P p) throws Exception {
        Date date = new Date();
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setShipSn(p.getString("ship_sn"));
        orderEntity.setOrderId(p.getInt("order_id"));
        orderEntity.setShipChannel(p.getString("ship_channel"));
        orderEntity.setShipTime(date);
        orderEntity.setUpdateTime(date);
        return R.success("success",iOrderService.updateById(orderEntity));
    }

    /**
     * 删除订单
     * @param p
     * @return
     */
    @RequestMapping("/delOrders")
    public R delOrders(@Pmap P p){
        if (iOrderService.removeById(p.getInt("order_id"))) {
            return R.success("删除成功");
        }
        else{
            return R.error("删除失败");
        }
    }

    /**
     * 增加订单
     * @param p
     * @return
     */
    public R insertOrders(@Pmap P p,OrderEntity orderEntity){

        return R.success("success",iOrderService.save(orderEntity));
    }
}
