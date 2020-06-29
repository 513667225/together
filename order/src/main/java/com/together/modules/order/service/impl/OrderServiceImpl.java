package com.together.modules.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.together.modules.order.entity.OrderEntity;
import com.together.modules.order.mapper.OrderMapper;
import com.together.modules.order.service.IOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.together.util.P;
import org.springframework.stereotype.Service;

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

    @Override
    public IPage getOrderPage(P p) {
        Integer page = p.getInt("page");
        Integer limit = p.getInt("limit");
        Page page1 = new Page(page,limit);
        Integer userId = (Integer)p.get("userId");
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("userId",userId);
        return baseMapper.selectPage(page1,queryWrapper);
    }
}
