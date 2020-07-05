package com.together.modules.order.provider;

import java.util.Map;

public class SqlProvider {

    public String getOrderPage(Map<String, Object> params){
        String sql ="select o.*,u.user_mobile,s.shop_name from " +
                "order_bot o left join user u on o.user_id = u.user_id " +
                "left join shop s on o.shop_id = s.shop_id " +
                "limit #{rowIndex},#{limit}";
        return sql;
    }

    public String getOrderPageConut(Map<String, Object> params){
        String sql ="SELECT COUNT(0)  from order_bot o left join user u on o.user_id = u.user_id ";
        return sql;
    }

    public String queryOrderGoods(Map<String, Object> params){
        String sql ="select og.*,g.goods_name,g.goods_price,o.order_sn,s.shop_name,s.shop_address from order_goods og \n" +
                "LEFT JOIN order_bot o on og.order_id = o.order_id \n" +
                "LEFT join goods g ON og.goods_id = g.goods_id \n" +
                "left join shop s on g.shop_id=s.shop_id\n" +
                "where og.order_id= #{order_id}";
        return sql;
    }
}
