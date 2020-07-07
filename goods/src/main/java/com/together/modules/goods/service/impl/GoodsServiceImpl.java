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
        GoodsEntity goodsEntity = new GoodsEntity();
        //goods_id	goods_name	category_id	shop_id	goods_gallery	goods_price	goods_brief	is_on_sale	sort_order
        // pic_url	goods_unit	goods_detail	goods_level	spokesman_id	add_time	update_time	goods_inventory
        goodsEntity.setGoodsName((String) map.get("goodsName"));
        goodsEntity.setCategoryId((Integer) map.get("categoryId2"));
        goodsEntity.setShopId((Integer) map.get("shopId"));
        goodsEntity.setGoodsGallery((String) map.get("goodsGallery"));
        goodsEntity.setGoodsPrice((BigDecimal) map.get("goodsPrice"));
        goodsEntity.setGoodsBrief((String) map.get("goodsBrief"));
        if("on".equals((String) map.get("isOnSale"))){
            goodsEntity.setIsOnSale(true);
        }else{
            goodsEntity.setIsOnSale(false);
        }
        goodsEntity.setSortOrder(0);
        goodsEntity.setPicUrl((String) map.get("picUrl"));
        goodsEntity.setGoodsUnit((String) map.get("goodsUnit"));
        goodsEntity.setGoodsDetail((String) map.get("goodsDetail"));
        BigDecimal goodsPrice = (BigDecimal) map.get("goodsPrice");
        if(goodsPrice.compareTo(BigDecimal.valueOf(99)) == 1 && goodsPrice.compareTo(BigDecimal.valueOf(199))== -1){
            goodsEntity.setGoodsLevel(1);
        }else if(goodsPrice.compareTo(BigDecimal.valueOf(199)) == 1 && goodsPrice.compareTo(BigDecimal.valueOf(299))== -1){
            goodsEntity.setGoodsLevel(2);
        }else if(goodsPrice.compareTo(BigDecimal.valueOf(299)) == -1){
            goodsEntity.setGoodsLevel(3);
        }else{
            goodsEntity.setGoodsLevel(0);
        }
        goodsEntity.setSpokesmanId(0);
        goodsEntity.setGoodsInventory((Integer) map.get("goodsInventory"));
        goodsEntity.setAddTime(new Date());
        goodsEntity.setUpdateTime(new Date());
        goodsMapper.insert(goodsEntity);
        Integer goodsId = goodsEntity.getGoodsId();
        JSONArray attributeEntity = JSONObject.parseArray((String) map.get("attributeEntity"));
        if (attributeEntity.size()>0){
            for (int i = 0; i < attributeEntity.size(); i++) {
                GoodsAttributeEntity goodsAttributeEntity = new GoodsAttributeEntity();
                JSONObject jsonObject = attributeEntity.getJSONObject(i);
                goodsAttributeEntity.setGoodsId(goodsId);
                goodsAttributeEntity.setAttributeName((String) jsonObject.get("attributeName"));
                goodsAttributeEntity.setAttributeName((String) jsonObject.get("attributeValue"));
                goodsAttributeEntity.setAddTime(new Date());
                goodsAttributeEntity.setUpdateTime(new Date());
                goodsAttributeMapper.insert(goodsAttributeEntity);
            }
        }
        GoodsSpecificationEntity goodsSpecificationEntity = new GoodsSpecificationEntity();
        goodsSpecificationEntity.setGoodsId(goodsId);
        goodsSpecificationEntity.setSpecificationName((String) map.get("specificationName"));
        goodsSpecificationEntity.setPicUrl((String) map.get("specificationPic"));
        goodsSpecificationEntity.setAddTime(new Date());
        goodsSpecificationEntity.setUpdateTime(new Date());
        int insert = goodsSpecificationMapper.insert(goodsSpecificationEntity);
        return R.success("success",insert);
    }




}