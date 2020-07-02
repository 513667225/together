package com.together.modules.shopUser.controller;


import com.together.annotation.Pmap;
import com.together.modules.shop.service.impl.ShopServiceImpl;
import com.together.modules.shopUser.entity.ShopUserEntity;
import com.together.modules.shopUser.service.IShopUserService;
import com.together.util.P;
import com.together.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 
 * @since 2020-07-02
 */
@RestController
@RequestMapping("/shopUser")
public class ShopUserController {

    @Autowired
    IShopUserService iShopUserService;

    @RequestMapping("/shopUserLogin")
    public R shopUserLogin(@Pmap P p) throws Exception {
        ShopUserEntity shopUserEntity = iShopUserService.shopUserLogin(p);
        if (shopUserEntity!=null){
            return R.success().data(shopUserEntity);
        }else{
            return R.error();
        }
    }

    @RequestMapping("/shopUserregister")
    public R shopUserReRister(@Pmap P p) throws Exception {
        ShopUserEntity shopUserEntity = iShopUserService.shopUserRegister(p);
        return R.success().data(shopUserEntity);
    }
}

