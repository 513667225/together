package com.together.modules.shopMenu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.together.modules.shopMenu.entity.ShopMenuEntity;
import com.together.util.R;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 
 * @since 2020-06-27
 */
public interface IShopMenuService extends IService<ShopMenuEntity> {
     R getMenu();

     R getOrder(Map<String,Object> map);

}
