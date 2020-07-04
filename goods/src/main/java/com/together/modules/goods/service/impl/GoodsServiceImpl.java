package com.together.modules.goods.service.impl;

import com.together.modules.goods.entity.GoodsEntity;
import com.together.modules.goods.mapper.GoodsMapper;
import com.together.modules.goods.service.IGoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.together.util.MapUtil;
import com.together.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商品基本信息表 服务实现类
 * </p>
 *
 * @author 
 * @since 2020-06-28
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, GoodsEntity> implements IGoodsService {

    @Autowired
    GoodsMapper goodsMapper;

    @Override
    public R queryGoodsByShopId(Map<String, Object> map) throws Exception {
        int goodsByShopIdCount = goodsMapper.queryGoodsByShopIdCount(map);
        List<Map<String, Object>> maps = goodsMapper.queryGoodsByShopId(map);
        for (Map<String, Object> stringObjectMap : maps) {
            MapUtil.mapKeySetLine2Upper(stringObjectMap);
        }
        return R.success().data(maps).set("total",goodsByShopIdCount);
    }



}
