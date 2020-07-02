package com.together.modules.shopUser.service;

import com.together.modules.shopUser.entity.ShopUserEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.together.util.P;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 
 * @since 2020-07-02
 */
public interface IShopUserService extends IService<ShopUserEntity> {

    ShopUserEntity shopUserRegister(P p) throws Exception;

    ShopUserEntity shopUserLogin(P p) throws Exception;
}
