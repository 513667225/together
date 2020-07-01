package com.together.modules.shop.controller;


import com.baomidou.mybatisplus.extension.api.R;
import com.together.annotation.Pmap;
import com.together.modules.shop.service.IShopService;
import com.together.util.P;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
     * 根据user_id查询店铺信息
     * @param p
     * @return
     */
    @GetMapping("/getShopPage")
    public R getShopPage(@Pmap P p) throws Exception{
        System.out.println(p);
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
