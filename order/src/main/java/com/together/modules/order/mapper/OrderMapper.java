package com.together.modules.order.mapper;

import com.together.modules.order.entity.OrderEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.together.modules.order.provider.SqlProvider;
import com.together.util.R;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 订单表 Mapper 接口
 * </p>
 *
 * @author 
 * @since 2020-06-28
 */
public interface OrderMapper extends BaseMapper<OrderEntity> {

    @SelectProvider(value = SqlProvider.class,method = "queryOrderPage")
    public List<Map<String,Object>> queryOrderPage(Map<String, Object> map);

    @SelectProvider(value = SqlProvider.class,method = "queryOrderPageCount")
    public int queryOrderPageCount(Map<String, Object> map);

    /**
     * 根据订单id查询该订单的所有商品集合
     */
    @SelectProvider(value = SqlProvider.class,method = "queryOrderGoods")
    public List<Map<String,Object>>  queryOrderGoods(Map<String, Object> map);

    public List<Map<String,Object>>  queryOrderByShopId(Map<String, Object> map);

}
