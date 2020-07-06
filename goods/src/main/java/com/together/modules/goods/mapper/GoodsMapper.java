package com.together.modules.goods.mapper;

import com.together.modules.goods.entity.GoodsEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.together.modules.provider.SQLProvider;
import jdk.internal.org.objectweb.asm.tree.analysis.Value;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.SelectProvider;

import java.time.temporal.ValueRange;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商品基本信息表 Mapper 接口
 * </p>
 *
 * @author 
 * @since 2020-06-28
 */
public interface GoodsMapper extends BaseMapper<GoodsEntity> {
    List<Map<String, Object>> queryGoodsByShopId(Map<String, Object> map);

    int queryGoodsByShopIdCount(Map<String, Object> map);

    @SelectProvider(value = SQLProvider.class,method = "queryAllGoods")
    List<Map<String, Object>> queryAllGoods(Map<String, Object> map);

//    @InsertProvider(value = SQLProvider.class, method = "addGoods")
//    int addGoods(Map<String, Object> map);

}
