package com.together.modules.provider;

import com.together.util.MapUtil;

import java.util.Map;

public class SQLProvider {

    public String  queryAllGoods(Map<String, Object> map){
//        String wheresql = MapUtil.mapToSql(map);
        String likeSql = MapUtil.mapToSqlByLike(map,"goods_name");
//        System.out.println();
        String sql ="SELECT * FROM goods "+likeSql;
        return sql;
    }

    public String addGoods(Map<String, Object> map){
        String s = "INSERT INTO `goods`";
        return s+MapUtil.mapToAddSql(map);
    }

    public String queryLimitNature(Map<String, Object> map){
        String sql="select goods_id, goods_name, category_id, shop_id, goods_gallery, goods_price, goods_brief, is_on_sale, sort_order, pic_url, goods_unit, goods_detail, goods_level, spokesman_id, add_time, update_time, goods_inventory, goods_nature from goods where goods_nature="
                +map.get("goods_nature")
                +" order by update_time desc limit "
                +map.get("limit");
        return sql;
    }

}
