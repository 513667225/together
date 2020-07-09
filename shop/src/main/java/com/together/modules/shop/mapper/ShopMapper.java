package com.together.modules.shop.mapper;

import com.together.modules.shop.entity.ShopEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.together.modules.shop.provider.SQLProvider;
import com.together.modules.shopUser.entity.ShopUserEntity;
import com.together.util.P;
import org.apache.ibatis.annotations.SelectProvider;

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

    List<ShopEntity> queryLimitHotShop(P p);

    List<ShopEntity> queryLimitRollShop(P p);

    @SelectProvider(value= SQLProvider.class,method = "queryRegion")
    List<Map<String,Object>> queryRegion(P p);


}
