package com.together.util;

import java.util.*;

/**
 * map操作帮助类
 * @author  Agu
 */
public class MapUtil {

    /**
     * 将map里面所有key 生成为sql语句 同时驼峰转下划线
     * @param map
     * @return
     * @throws Exception
     */
    public  static String mapToSqlUpper2Line(Map map)throws Exception{
        mapKeySetUpper2Line(map);
        return mapToSql(map);
    }

    /**
     * 将map里面所有key 生成为sql语句 同时下划线转驼峰
     * @param map
     * @return
     * @throws Exception
     */
    public  static String mapToSqlLine2Upper(Map map) throws Exception{
        mapKeySetLine2Upper(map);
        return mapToSql(map);
    }


    /**
     * map转化为sql 格式:key=#{key}
     * @param map
     * @return
     */
    public  static String mapToSql(Map map){
        Set<Map.Entry> set = map.entrySet();
        String sql = "";
        for (Map.Entry entry : set) {
            String key = String.valueOf(entry.getKey());
            String value = String.valueOf(entry.getValue());
            sql += key+"=#{"+key+"} and ";
        }
        sql = sql.substring(0,sql.lastIndexOf("and"));
        return sql;
    }


    /**
     * map里面所有key 驼峰转下划线
     * @param map
     * @return
     * @throws Exception
     */
    public static void mapKeySetUpper2Line(Map map) throws Exception {
        Set<Map.Entry> set = map.entrySet();
        Set removeSet = new HashSet();
        Map map1 = new HashMap();
        for (Map.Entry entry : set) {
            String s = Map2JavaBeanUtil.transUpper2UnderLine(entry.getKey().toString());
            map1.put(s,entry.getValue());
            removeSet.add(entry.getKey());
        }
        map.putAll(map1);
        map.keySet().removeAll(removeSet);
    }

    /**
     * map里面所有key 下划线转驼峰
     * @param map
     * @return
     * @throws Exception
     */
    public static void mapKeySetLine2Upper(Map map) throws Exception {
        Set<Map.Entry> set = map.entrySet();
        Set removeSet = new HashSet();
        Map map1 = new HashMap();
        for (Map.Entry entry : set) {
            String s = Map2JavaBeanUtil.transUnderLine2Upper(entry.getKey().toString());
            map1.put(s,entry.getValue());
            removeSet.add(entry.getKey());
        }
        map.putAll(map1);
        map.keySet().removeAll(removeSet);
    }



}
