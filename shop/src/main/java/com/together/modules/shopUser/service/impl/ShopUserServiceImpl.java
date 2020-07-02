package com.together.modules.shopUser.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.together.modules.shopUser.entity.ShopUserEntity;
import com.together.modules.shopUser.mapper.ShopUserMapper;
import com.together.modules.shopUser.service.IShopUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.together.util.P;
import com.together.util.utli.RedisIdUtil;
import com.together.util.utli.ValidateUtli;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 
 * @since 2020-07-02
 */
@Service
public class ShopUserServiceImpl extends ServiceImpl<ShopUserMapper, ShopUserEntity> implements IShopUserService {

    @Override
    public ShopUserEntity shopUserRegister(P p) throws Exception {
        ValidateUtli.validateParams(p,"shopuserName","shopuserPassword");
        QueryWrapper<ShopUserEntity> shopUserEntityQueryWrapper = new QueryWrapper<ShopUserEntity>();
        shopUserEntityQueryWrapper.eq("shopuser_name",p.getString("shopuserName"))
                .eq("shopuser_password",p.getString("shopuserPassword"));
        ShopUserEntity shopUserEntity = baseMapper.selectOne(shopUserEntityQueryWrapper);
        if(shopUserEntity==null){
            shopUserEntity=new ShopUserEntity();
            shopUserEntity.setAddTime(new Date());
            shopUserEntity.setShopuserName(p.getString("shopuserName"));
            shopUserEntity.setUpdateTime(new Date());
            shopUserEntity.setShopuserPassword(p.getString("shopuserName"));
            shopUserEntity.setShopuserId(RedisIdUtil.nextId("shop_user_id"));
            baseMapper.insert(shopUserEntity);
        }
        return shopUserEntity;
    }

    @Override
    public ShopUserEntity shopUserLogin(P p) throws Exception {
        ValidateUtli.validateParams(p,"shopuserName","shopuserPassword");
        QueryWrapper<ShopUserEntity> shopUserEntityQueryWrapper = new QueryWrapper<ShopUserEntity>();
        shopUserEntityQueryWrapper.eq("shopuser_name",p.getString("shopuserName"))
                .eq("shopuser_password",p.getString("shopuserPassword"));
        ShopUserEntity shopUserEntity = baseMapper.selectOne(shopUserEntityQueryWrapper);
        return shopUserEntity;
    }
}
