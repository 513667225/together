package com.together.modules.shop.controller;


import com.together.modules.shop.serviceClient.ProductServiceClient;
import com.together.modules.shopMenu.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 
 * @since 2020-06-26
 */
@RestController
@RequestMapping("/shop")
public class ShopController {

    @Autowired
    ProductServiceClient productServiceClient;

    @GetMapping("test")
    public R test(){
        Map<String,Object> map = new HashMap<>();
        map.put("page",1);
        map.put("limit",2);
        System.out.println(productServiceClient.getProductPage1(map));
        return R.success();
    }

}
