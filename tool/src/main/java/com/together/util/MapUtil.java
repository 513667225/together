package com.together.util;

import java.math.BigDecimal;
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

    /**
     * 根据key返回String类型的值
     * @param map
     * @param key
     * @return
     */
    public static String getString(Map map,String key){
        Object o = map.get(key);
        if (o == null) {
            return null;
        }
        return String.valueOf(o);
    }

    /**
     * 根据key返回int类型的值
     * @param map
     * @param key
     * @return
     */
    public static Integer getInt(Map map,String key){
        String string = getString(map, key);
        if ( string== null) {
            return null;
        }
        return Integer.parseInt(string);
    }

    /**
     * 根据key返回Double类型的值
     * @param map
     * @param key
     * @return
     */
    public static Double getDouble(Map map,String key){
        return Double.parseDouble(getString(map, key));
    }

    /**
     * 根据key返回Long类型的值
     * @param map
     * @param key
     * @return
     */
    public static Long getLong(Map map,String key){
        return Long.parseLong(getString(map, key));
    }

    /**
     * 根据key返回Float类型的值
     * @param map
     * @param key
     * @return
     */
    public static Float getFloat(Map map,String key){
        return Float.parseFloat(getString(map, key));
    }

}
