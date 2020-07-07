package com.together.modules.goods.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.together.modules.goods.entity.GoodsEntity;
import com.together.modules.goods.mapper.GoodsMapper;
import com.together.modules.goods.service.IGoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.together.modules.goodsAttribute.entity.GoodsAttributeEntity;
import com.together.modules.goodsAttribute.mapper.GoodsAttributeMapper;
import com.together.modules.goodsSpecification.entity.GoodsSpecificationEntity;
import com.together.modules.goodsSpecification.mapper.GoodsSpecificationMapper;
import com.together.util.MapUtil;
import com.together.util.P;
import com.together.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.crypto.Data;
import java.math.BigDecimal;
import java.util.Date;
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
        P p = (P) map;
        GoodsEntity goodsEntity = new GoodsEntity();
        //goods_id	goods_name	category_id	shop_id	goods_gallery	goods_price	goods_brief	is_on_sale	sort_order
        // pic_url	goods_unit	goods_detail	goods_level	spokesman_id	add_time	update_time	goods_inventory
        goodsEntity.setGoodsName(p.getString("goodsName"));
        goodsEntity.setCategoryId(p.getInt("category2"));
        goodsEntity.setShopId(p.getInt("shopId"));
        goodsEntity.setGoodsGallery(p.getString("goodsGallery"));
        goodsEntity.setGoodsPrice(p.getBigDecimal("goodsPrice"));
        goodsEntity.setGoodsBrief(p.getString("goodsBrief"));
        if("on".equals(p.getString("isOnSale"))){
            goodsEntity.setIsOnSale(true);
        }else{
            goodsEntity.setIsOnSale(false);
        }
        goodsEntity.setSortOrder(0);
        goodsEntity.setPicUrl(p.getString("picUrl"));
        goodsEntity.setGoodsUnit(p.getString("goodsUnit"));
        goodsEntity.setGoodsDetail(p.getString("goodsDetail"));
        goodsEntity.setSpokesmanId(0);
        goodsEntity.setGoodsInventory(p.getInt("goodsInventory"));
        goodsEntity.setAddTime(new Date());
        goodsEntity.setUpdateTime(new Date());
        goodsMapper.insert(goodsEntity);
        Integer goodsId = goodsEntity.getGoodsId();
        JSONArray attributeEntity = JSONObject.parseArray(p.getString("attributeEntity"));
        if (attributeEntity.size()>0){
            for (int i = 0; i < attributeEntity.size(); i++) {
                GoodsAttributeEntity goodsAttributeEntity = new GoodsAttributeEntity();
                JSONObject jsonObject = attributeEntity.getJSONObject(i);
                goodsAttributeEntity.setGoodsId(goodsId);
                goodsAttributeEntity.setAttributeName((String) jsonObject.get("attributeName"));
                goodsAttributeEntity.setAttributeValue((String) jsonObject.get("attributeValue"));
                goodsAttributeEntity.setAddTime(new Date());
                goodsAttributeEntity.setUpdateTime(new Date());
                goodsAttributeMapper.insert(goodsAttributeEntity);
            }
        }
        GoodsSpecificationEntity goodsSpecificationEntity = new GoodsSpecificationEntity();
        goodsSpecificationEntity.setGoodsId(goodsId);
        goodsSpecificationEntity.setSpecificationName(p.getString("specificationName"));
        goodsSpecificationEntity.setPicUrl(p.getString("specificationPic"));
        goodsSpecificationEntity.setAddTime(new Date());
        goodsSpecificationEntity.setUpdateTime(new Date());
        int insert = goodsSpecificationMapper.insert(goodsSpecificationEntity);
        return R.success("success",insert);
    }




}