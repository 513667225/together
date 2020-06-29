package com.together.modules.order.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.together.modules.order.entity.OrderEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.together.util.P;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <p>
 * 订单表 服务类
 * </p>
 *
 * @author liuhuan
 * @since 2020-06-28
 */
public interface IOrderService extends IService<OrderEntity> {

    /**
     * 根据用户id查询订单信息+分页
     */
    IPage getOrderPage(@RequestParam("p")P p);

    /**
     * 增加订单方法
     */
}
