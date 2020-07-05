package com.together.modules.shopUser.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.together.annotation.Pmap;
import com.together.modules.shop.service.impl.ShopServiceImpl;
import com.together.modules.shopUser.entity.ShopUserEntity;
import com.together.modules.shopUser.service.IShopUserService;
import com.together.util.P;
import com.together.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

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
    IShopUserService shopUserService;

    @GetMapping("/getShopUserPage")
    public R getShopUserPage(@Pmap P p) throws Exception {
        Integer page = p.getInt("page");
        Integer limit = p.getInt("limit");
        p.initPageArg();
        Page<ShopUserEntity> objectPage = new Page<>(page,limit);
        p.remove("page");
        p.remove("limit");
        p.remove("rowIndex");
        if(""==p.getString("shopuser_name")){
            p.remove("shopuser_name");
        }
        Page<ShopUserEntity> pageObject = shopUserService.page(objectPage,new QueryWrapper<ShopUserEntity>().allEq(p));
        return R.success("success",pageObject.getRecords()).set("count",pageObject.getTotal());
    }

    /**
     * 商家登录方法
     */
    @RequestMapping("loinShopUser")
    public R loinShopUser (@Pmap P p, HttpServletRequest request) throws Exception {
        ShopUserEntity shopUserEntity = new ShopUserEntity();
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("shopuser_name", p.getString("shopuser_name"));
        queryWrapper.eq("shopuser_password", p.getString("shopuser_password"));
        ShopUserEntity one = shopUserService.getOne(queryWrapper);
        if (null!=one||!"".equals(one)){
            one.setUpdateTime(new Date());
            one.setAddTime(new Date());
            shopUserService.updateById(one);
        }
        request.getSession().setAttribute("shopUserEntity",one);
        p.setRequest(request);
//        System.out.println(request.getSession().getAttribute("shopUserEntity"));
        return R.success("success",one);
    }
}

