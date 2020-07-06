package com.together.modules.goods.service;

import com.together.modules.goods.entity.GoodsEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.together.util.R;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商品基本信息表 服务类
 * </p>
 *Map<String,Object>
 *     数据
 *
 * @author 
 * @since 2020-06-28
 */
public interface IGoodsService extends IService<GoodsEntity> {

    R queryGoodsByShopId(Map<String, Object> map) throws Exception;

    R queryAllGoods(Map<String, Object> map) throws Exception;

}
