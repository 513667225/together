package com.together.modules.goods.service.impl;

import com.together.modules.goods.entity.GoodsEntity;
import com.together.modules.goods.mapper.GoodsMapper;
import com.together.modules.goods.service.IGoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.together.modules.goodsAttribute.mapper.GoodsAttributeMapper;
import com.together.modules.goodsSpecification.mapper.GoodsSpecificationMapper;
import com.together.util.MapUtil;
import com.together.util.P;
import com.together.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    GoodsAttributeMapper goodsAttributeMapper;

    @Autowired
    GoodsSpecificationMapper goodsSpecificationMapper;

    @Override
    public R queryGoodsByShopId(Map<String, Object> map) throws Exception {
        int goodsByShopIdCount = goodsMapper.queryGoodsByShopIdCount(map);
        List<Map<String, Object>> maps = goodsMapper.queryGoodsByShopId(map);
        for (Map<String, Object> stringObjectMap : maps) {
            MapUtil.mapKeySetLine2Upper(stringObjectMap);
        }
        return R.success().data(maps).set("total",goodsByShopIdCount);
    }

    //
    @Override
    public R queryAllGoods(Map<String, Object> map) throws Exception {
        return R.success("msg",goodsMapper.queryAllGoods(map));
    }


    @Transactional
    @Override
    public R addGoods(Map<String, Object> map) {
        GoodsEntity goodsEntity = new GoodsEntity();
        //goods_id	goods_name	category_id	shop_id	goods_gallery	goods_price	goods_brief	is_on_sale	sort_order
        // pic_url	goods_unit	goods_detail	goods_level	spokesman_id	add_time	update_time	goods_inventory
        goodsEntity.setGoodsName((String) map.get("goodsName"));
        goodsEntity.setGoodsName((String) map.get("goods"));
        int insertGoods = goodsMapper.insert(goodsEntity);
        return R.success("success",insertGoods);
    }

    @Override
    public List<GoodsEntity> queryLimitNature(P p) {
        List<GoodsEntity> goodsEntities=baseMapper.queryLimitNature(p);
        return goodsEntities;
    }


}