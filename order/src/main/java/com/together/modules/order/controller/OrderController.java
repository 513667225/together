package com.together.modules.order.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.together.annotation.Pmap;
import com.together.entity.GoodsEntity;
import com.together.modules.order.entity.OrderEntity;
import com.together.modules.order.service.IOrderService;
import com.together.modules.order.serviceClient.GoodsServiceClient;
import com.together.util.P;
import com.together.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/getOrderPage")
    public R getOrderPage(@Pmap P p)throws Exception{
        Integer page = p.getInt("page");
        Integer limit = p.getInt("limit");
        p.initPageArg();
        Page<OrderEntity> objectPage = new Page<>(page,limit);
        p.remove("page");
        p.remove("limit");
        p.remove("rowIndex");
        if(""==p.getString("order_tel")){
            p.remove("order_tel");
        }
        if(""==p.getString("order_consignee")){
            p.remove("order_consignee");
        }
        if(""==p.getString("ship_sn")){
            p.remove("ship_sn");
        }
        Page<OrderEntity> pageObject = iOrderService.page(objectPage,new QueryWrapper<OrderEntity>().allEq(p).orderByDesc("add_time"));
        return R.success("success",pageObject.getRecords()).set("count",pageObject.getTotal());
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
     * 根据用户id查询所有订单
     * @param p
     * @return
     */
    @RequestMapping("/getUserOrders")
    public R getUserOrders(@Pmap P p) throws Exception {
        p.batchToInt("userId");
        p.batchToInt("page","limit");
        return iOrderService.queryOrderByShopId(p);
    }


    /**
     * 根据用户id及订单状态查询订单
     * @param p
     * @return
     */
    @RequestMapping("/getUserOrdersEqStatus")
    public R getUserOrdersEqStatus(@Pmap P p) throws Exception {
        p.batchToInt("userId");
        p.batchToInt("orderStatus");
        p.batchToInt("page","limit");
        return iOrderService.getUserOrdersEqStatus(p);
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
        orderEntity.setOrderStatus(102);
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
    public R insertOrders(@Pmap P p,OrderEntity orderEntity,GoodsEntity goodsEntity){
        boolean save = iOrderService.save(orderEntity);
        if (orderEntity.getOrderId()!=0){
        }
        return R.success("success",iOrderService.save(orderEntity));
    }

    @PostMapping("/newOrder")
    public R newOrder(@Pmap P p) throws Exception {
        return R.success("success",iOrderService.newOrder(p));
    }

}
