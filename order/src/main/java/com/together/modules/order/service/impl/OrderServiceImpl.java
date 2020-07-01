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


//    @Override
//    public IPage getOrderPage(P p) {

//    }

    @Override
    public List<OrderEntity> getOrderPage(P p) {
                Integer page = p.getInt("page");
        Integer limit = p.getInt("limit");
        p.initPageArg();
        Page<OrderEntity> page1  = new Page(page, limit);
        Integer userId = p.getInt("userId");
        QueryWrapper<OrderEntity> queryWrapper = new QueryWrapper<OrderEntity>();
        queryWrapper.eq("user_id",userId);
        return  baseMapper.selectPage(page1,queryWrapper).getRecords();
    }

    @Override
    public int insertOrderById(P p) {
        OrderEntity orderEntity = (OrderEntity) p.get("order");
        return baseMapper.insert(orderEntity);
    }

    @Override
    public int updById(P p) {
        OrderEntity orderEntity = (OrderEntity) p.get("order");
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("orderId",orderEntity.getOrderId());
        return baseMapper.update(orderEntity,queryWrapper);
    }

    @Override
    public int delById(P p) {
        OrderEntity orderEntity = (OrderEntity) p.get("order");
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("orderId",orderEntity.getOrderId());
        return baseMapper.delete(queryWrapper);
    }
}
