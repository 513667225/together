package com.together.util;

import java.util.*;

public class MapUtil {


    public  static String mapToSqlUpper2Line(Map map)throws Exception{
        return mapToSql(mapKeySetUpper2Line(map));
    }

    public  static String mapToSqlLine2Upper(Map map) throws Exception{
        return mapToSql(mapKeySetLine2Upper(map));
    }

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

    public static void main(String[] args) throws Exception {
        HashMap<String, String> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("xx_x","123");
        objectObjectHashMap.put("xx1","123");
        objectObjectHashMap.put("xx2","123");
        System.out.println(mapToSqlLine2Upper(objectObjectHashMap));
    }




    public static Map mapKeySetUpper2Line(Map map) throws Exception {
        Set<Map.Entry> set = map.entrySet();
        Map map1 = new HashMap();
        for (Map.Entry entry : set) {
            String s = Map2JavaBeanUtil.transUpper2UnderLine(entry.getKey().toString());
            map1.put(s,entry.getValue());
        }
        return map1;
    }

    public static Map mapKeySetLine2Upper(Map map) throws Exception {
        Set<Map.Entry> set = map.entrySet();
        Map map1 = new HashMap();
        for (Map.Entry entry : set) {
            String s = Map2JavaBeanUtil.transUnderLine2Upper(entry.getKey().toString());
            map1.put(s,entry.getValue());
//            removeSet.add(entry.getKey());
        }
        return map1;
//        map.keySet().remove(removeSet);
    }


}