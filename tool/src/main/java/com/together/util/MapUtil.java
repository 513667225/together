package com.together.util;

import java.util.Map;
import java.util.Set;

public class MapUtil {


    public static void mapKeySetUpper2Line(Map map) throws Exception {
//        this.keySet()
//        return thisToEntity(toClass);
        Set<Map.Entry> set = map.entrySet();
        for (Map.Entry entry : set) {
            String s = Map2JavaBeanUtil.transUpper2UnderLine(entry.getKey().toString());
            map.put(s,entry.getValue());
            map.remove(entry.getKey());
        }
    }

    public static void mapKeySetLine2Upper(Map map) throws Exception {
        Set<Map.Entry> set = map.entrySet();
        for (Map.Entry entry : set) {
            String s = Map2JavaBeanUtil.transUnderLine2Upper(entry.getKey().toString());
            map.put(s,entry.getValue());
            map.remove(entry.getKey());
        }
    }


}
