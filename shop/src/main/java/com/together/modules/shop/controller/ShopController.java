package com.together.modules.shop.controller;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.together.annotation.Pmap;
import com.together.modules.shop.entity.ShopEntity;
import com.together.modules.shop.service.IShopService;
import com.together.util.Map2JavaBeanUtil;
import com.together.util.P;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 
 * @since 2020-06-28
 */
@RestController
@RequestMapping("/shop")
public class ShopController {

    @Autowired
    private IShopService shopService;

    /**
     * 根据商品id查询店铺信息
     * @param p
     * @return
     */
    @GetMapping("/getShopPage")
    public R getUserByUserId(@Pmap P p){
        return R.ok(shopService.getShopPage(p));
    }

    /**
     * 新增
     * @param p
     * @return
     */
    @PostMapping
    public R saveShopById(@Pmap P p){
        return R.ok(shopService.saveShopById(p));
    }

    /**
     * 修改
     * @param p
     * @return
     */
    @PutMapping
    public R updShopById(@Pmap P p){
        return R.ok(shopService.updShopById(p));
    }


}
