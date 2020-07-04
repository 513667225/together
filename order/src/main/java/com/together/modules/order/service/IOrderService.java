package com.together.modules.order.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.together.modules.order.entity.OrderEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.together.util.P;
import com.together.util.R;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 订单表 服务类
 * </p>
 *
 * @author liuhuan
 * @since 2020-06-28
 */
public interface IOrderService extends IService<OrderEntity> {



    R getOrderPage(P p) throws Exception;

    int getOrderPageConut(P p);

    R queryOrderGoods(P p) throws Exception;

    R queryOrderByShopId(P p) throws Exception;





}
