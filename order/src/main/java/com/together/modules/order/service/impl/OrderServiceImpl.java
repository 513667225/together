package com.together.modules.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.together.modules.order.entity.OrderEntity;
import com.together.modules.order.mapper.OrderMapper;
import com.together.modules.order.service.IOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.together.util.P;
import com.together.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author 
 * @since 2020-06-28
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, OrderEntity> implements IOrderService {

    @Autowired
    OrderMapper orderMapper;

    @Override
    public R getOrderPage(P p) {
        List<Map<String, Object>> maps = orderMapper.queryOrderPage(p);
        return R.success().data(maps).set("count",getOrderPageConut(p));
    }

    @Override
    public int getOrderPageConut(P p) {
        return orderMapper.queryOrderPageCount(p);
    }

    @Override
    public R queryOrderGoods(P p) {
        List<Map<String, Object>> maps = orderMapper.queryOrderGoods(p);
        return R.success().data(maps);
    }
}
