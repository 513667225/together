package com.together.modules.shop.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.together.modules.shop.entity.ShopEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.together.util.P;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

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
     * 根据用户id查询店铺集合 分页
     * @return
     */
    IPage getShopPage(@RequestParam("p") P p);

    /**
     * 根据用户id新增店铺
     */
    int saveShopById(@RequestParam("p") P p);

    /**
     * 根据用户id修改店铺信息
     */
    int updShopById(@RequestParam("p") P p);
}
