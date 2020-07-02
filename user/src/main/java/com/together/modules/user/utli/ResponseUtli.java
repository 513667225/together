package com.together.modules.user.utli;

import java.util.HashMap;
import java.util.Map;

public class ResponseUtli {
    public static Map<String,Object> StringToMap(String key,Object value){
        Map<String, Object> stringObjectMap = new HashMap<>();
        if(value instanceof Integer){
            stringObjectMap.put(key,Integer.valueOf(String.valueOf(value)));
        }else{
            stringObjectMap.put(key,value);
        }
        return stringObjectMap;
    }

    public static Map<String,Object> NullToMap(){
        Map<String, Object> stringObjectMap = new HashMap<>();
        return stringObjectMap;
    }
}
