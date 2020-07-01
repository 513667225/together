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
    /**
     * 查询订单信息
     */

    @Autowired
    OrderMapper orderMapper;

    @RequestMapping("getOrderPage")
    public R getOrderPage(@Pmap P p){

        Integer page = p.getInt("page");
        Integer limit = p.getInt("limit");
        p.initPageArg();
        p.remove("page");
        p.remove("limit");
        p.remove("rowIndex");
        Page<OrderEntity> objectPage = new Page<>(1, 10);
        Page<OrderEntity> pageObject=orderMapper.selectPage(objectPage,new QueryWrapper<OrderEntity>().allEq(p));


        return R.success("success",pageObject.getRecords()).set("count",pageObject.getTotal());
    }



    /**
     * 新增
     */
    public R insertOrderById(@Pmap P p){
        return R.success("success",iOrderService.insertOrderById(p));
    }

    /**
     * 修改
     */



}
