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



    List<OrderEntity> getOrderPage(P p);

    /**
     * 增加用户ID订单
     */
    int insertOrderById(P p);

    /**
     * 根据用户ID修改订单信息
     */

    int updById(P p);

    /**
     * 根据订单ID删除订单
     */

    int delById(P p);



}
