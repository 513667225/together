package com.together.modules.shop.controller;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.together.modules.shop.entity.ShopEntity;
import com.together.modules.shop.service.IShopService;
import com.together.util.Map2JavaBeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/getShopPage")
    public R getGoodsPage(Map<String,Object> map) throws Exception {
        ShopEntity shopEntity = new ShopEntity();
        int pageNum = 1;
        int limit = 10;
        if (map.get("page")!=null) {
            pageNum = (int) map.get("page");
        }
        if (map.get("limit")!=null) {
            limit = (int) map.get("limit");
        }
        if(map.get("shop")!=null){
            shopEntity = (ShopEntity) map.get("shop");
        }
        Page page = new Page(pageNum,limit);
        Map2JavaBeanUtil.transMap2Bean(map,shopEntity);
        return R.ok(shopService.page(page, Wrappers.query(shopEntity)));
    }

}
