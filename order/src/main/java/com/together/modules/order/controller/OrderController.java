package com.together.modules.order.controller;


import com.baomidou.mybatisplus.extension.api.R;
import com.together.annotation.Pmap;
import com.together.modules.order.service.IOrderService;
import com.together.util.P;
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
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private IOrderService iOrderService;

    public R getOrderByUserId(@Pmap P p){
        return R.ok(iOrderService.getOrderPage(p));
    }




}
