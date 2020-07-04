package com.together.modules.shop.mapper;

import com.together.modules.shop.entity.ShopEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 
 * @since 2020-06-28
 */
public interface ShopMapper extends BaseMapper<ShopEntity> {

    int queryAllShopCount(Map<String, Object> map);

}
