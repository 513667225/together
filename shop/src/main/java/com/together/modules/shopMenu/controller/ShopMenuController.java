package com.together.modules.shopMenu.controller;


import com.together.modules.shopMenu.service.IShopMenuService;
import com.together.modules.shopMenu.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 
 * @since 2020-06-27
 */
@RestController
@RequestMapping("/shopMenu")
public class ShopMenuController {

    @Autowired
    IShopMenuService iShopMenuService;

    @RequestMapping("/getMenu")
    public R getMenu(){

        return iShopMenuService.getMenu();
    }


    @RequestMapping("/getOrder")
    public R getOrder(@RequestParam Map<String,Object> map){

        return iShopMenuService.getOrder(map);
    }




}
