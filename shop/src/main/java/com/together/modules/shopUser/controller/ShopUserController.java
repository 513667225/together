package com.together.modules.shopUser.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.together.annotation.Pmap;
import com.together.modules.shop.service.impl.ShopServiceImpl;
import com.together.modules.shopUser.entity.ShopUserEntity;
import com.together.modules.shopUser.service.IShopUserService;
import com.together.parameter.RedisParamenter;
import com.together.util.MapUtil;
import com.together.util.P;
import com.together.util.R;
import com.together.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.UUID;

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
    @Autowired
    ValueOperations operations;


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
    @GetMapping("/loinShopUser")
    public R loinShopUser (@Pmap P p) throws Exception {
        String shopuserPassword = p.getString("shopuserPassword");
        ShopUserEntity one = shopUserService.getOne(new QueryWrapper<ShopUserEntity>().eq("shopuser_name",p.getString("shopuserName")));
        if(null!=one){
            if(one.getShopuserPassword().equals(shopuserPassword)){
                String uuid = UUID.randomUUID().toString().replaceAll("-", "");//生成uuid
                operations.set(uuid,one);//将对象放在redis
                Cookie cookie = new Cookie(RedisParamenter.SHOP_LOING_USER_REDIS_KEY,uuid);
                HttpServletResponse response = p.getResponse();
                response.addCookie(cookie);
                return R.success("0",cookie);
            }else{
                return R.success("1");
            }
        }
        return R.success("-1");
    }

//用户名验证
    @GetMapping("/queyByName")
    public R queyByName(@Pmap P p){
        ShopUserEntity shopUserEntity = shopUserService.getOne(new QueryWrapper<ShopUserEntity>().eq("shopuser_name", p.getString("shopuser_name")));
        if (shopUserEntity==null){
            return R.success("");

        }else {
            return R.success("用户名已存在！请重新输入！");
        }
    }

    @RequestMapping("/addShopUser")
    //增加商家用户的方法
    public R addShopUser(@Pmap P p){
        ShopUserEntity shopUserEntity = new ShopUserEntity();
        shopUserEntity.setShopuserName(p.getString("shopuser_name"));
        shopUserEntity.setShopuserPassword(p.getString("shopuser_password"));
        shopUserEntity.setAddTime(new Date());
        shopUserEntity.setUpdateTime(new Date());
        return R.success("success",shopUserService.save(shopUserEntity));
    }

}

