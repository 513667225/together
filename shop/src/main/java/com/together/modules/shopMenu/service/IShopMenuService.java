package com.together.modules.shopMenu.service;

import com.together.modules.shopMenu.entity.ShopMenuEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.together.modules.shopMenu.service.impl.ShopMenuServiceImpl;
import com.together.modules.shopMenu.util.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

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
