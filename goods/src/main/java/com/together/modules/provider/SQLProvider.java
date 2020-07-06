package com.together.modules.provider;

import com.together.util.MapUtil;

import java.util.Map;

public class SQLProvider {

    public String  queryAllGoods(Map<String, Object> map){
//        String wheresql = MapUtil.mapToSql(map);
        String likeSql = MapUtil.mapToSqlByLike(map);
//        System.out.println();
        String sql ="SELECT * FROM goods "+likeSql;
        return sql;
    }

}
