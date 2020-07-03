package com.together.modules.shop.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.together.modules.shop.entity.ShopEntity;
import com.together.util.P;
import com.together.util.R;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 
 * @since 2020-06-28
 */
public interface IShopService extends IService<ShopEntity> {

    /**
     * 管理员接口,查询所有店铺信息
     */
    R queryAllShop(Map<String, Object> map);

    /**
     * 根据用户id查询店铺集合 分页
     * @return
     */
    IPage getShopPage(P p);

    /**
     * 根据用户id新增店铺
     */
    int saveShopById(P p);

    /**
     * 根据用户id修改店铺信息
     */
    int updShopById(P p);
}
