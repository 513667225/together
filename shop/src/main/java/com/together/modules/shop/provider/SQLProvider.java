package com.together.modules.shop.provider;

import com.together.util.MapUtil;
import com.together.util.P;

public class SQLProvider {

    public String queryRegion(P p){


        String sql="select * from region "+MapUtil.mapToSqlByLike(p);
        return sql;
}
}
