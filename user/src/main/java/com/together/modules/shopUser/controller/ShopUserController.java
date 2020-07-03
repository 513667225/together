package com.together.modules.shopUser.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.together.annotation.Pmap;
import com.together.modules.shopUser.entity.ShopUserEntity;
import com.together.modules.shopUser.service.IShopUserService;
import com.together.util.P;
import com.together.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
    private IShopUserService shopUserService;

    @GetMapping("/getShopUserPage")
    public R getShopUserPage(@Pmap P p) throws Exception {
        Integer page = p.getInt("page");
        Integer limit = p.getInt("limit");
        p.initPageArg();
        p.remove("page");
        p.remove("limit");
        p.remove("rowIndex");
        Page<ShopUserEntity> objectPage = new Page<>(1, 10);
        Page<ShopUserEntity> pageObject = shopUserService.page(objectPage,new QueryWrapper<ShopUserEntity>().allEq(p));
        return R.success("success",pageObject.getRecords()).set("count",pageObject.getTotal());
    }

}