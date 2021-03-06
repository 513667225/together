package com.together.modules.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.together.entity.UserAddressEntity;
import com.together.modules.order.entity.OrderEntity;
import com.together.modules.order.mapper.OrderMapper;
import com.together.modules.order.service.IOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.together.modules.order.service.client.UserAddressServiceClient;
import com.together.util.MapUtil;
import com.together.util.P;
import com.together.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
    @Autowired
    UserAddressServiceClient client;

    @Override
    public int getOrderPageConut(P p) {
        return orderMapper.getOrderPageConut(p);
    }

    @Override
    public R queryOrderGoods(P p) throws Exception {
        List<Map<String, Object>> maps = orderMapper.queryOrderGoods(p);
        for (Map<String, Object> map : maps) {
            MapUtil.mapKeySetLine2Upper(map);
        }
        return R.success().data(maps);
    }

    @Override
    public R queryOrderByShopId(P p) throws Exception {
        List<Map<String, Object>> maps = orderMapper.queryOrderByShopId(p);
        for (Map<String, Object> map : maps) {
            MapUtil.mapKeySetLine2Upper(map);
        }
        int i = orderMapper.queryOrderByShopIdCount(p);
        return R.success().data(maps).set("count",i);
    }

    @Override
    public R getUserOrdersEqStatus(P p) throws Exception {
        List<Map<String, Object>> maps = orderMapper.getUserOrdersEqStatus(p);
        for (Map<String, Object> map : maps) {
            MapUtil.mapKeySetLine2Upper(map);
        }
        return R.success().data(maps);
    }


    @Override
    public int newOrder(P p) throws Exception {
        //获取地址信息
        R r = client.selectByuserAddress(p);
        UserAddressEntity userAddressEntity = new UserAddressEntity();
        //转userAddressEntity实体
        r.thisToEntity(userAddressEntity);
        OrderEntity orderEntity = new OrderEntity();
        //转orderEntity实体
        p.thisToEntity(orderEntity);
        orderEntity.setOrderConsignee(userAddressEntity.getUserName());
        orderEntity.setShopId(userAddressEntity.getUserId());
        orderEntity.setOrderTel(userAddressEntity.getTel());
        orderEntity.setOrderAddress(userAddressEntity.getAddressDetail());
        //暂未使用，先写死
        orderEntity.setOrderSn("1");
        //暂未使用，先写死
        orderEntity.setFreightPrice(new BigDecimal(1));
        //暂未使用，先写死
        orderEntity.setPindouPrice(new BigDecimal(1));
        //暂未使用，先写死
        orderEntity.setOrderMessage("1");
        return baseMapper.insert(orderEntity);
    }
}
