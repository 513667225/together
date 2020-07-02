package com.together.util;

public class StringUtil {



    /**
     * 批量下划线转大写
     * @param keys
     * @return
     */
    public static String[] batchUnderLine2Upper(String... keys){
        if (keys==null){
            return  null;
        }
        String[] strings = new String[keys.length];
        for (int i = 0; i < strings.length; i++) {
            strings[i] = Map2JavaBeanUtil.transUnderLine2Upper(keys[i]);
        }
        return strings;
    }


    /**
     * 批量大写转下划线
     * @param keys
     * @return
     */
    public static String[] batchUpper2UnderLine(String... keys){
        if (keys==null){
            return  null;
        }
        String[] strings = new String[keys.length];
        for (int i = 0; i < strings.length; i++) {
            strings[i] = Map2JavaBeanUtil.transUpper2UnderLine(keys[i]);
        }
        return strings;
    }


}
