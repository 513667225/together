package com.together.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class P extends HashMap<String,Object> {

    private HttpServletRequest request;

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    private HttpServletResponse response;






    public P() {

    }

    public void thisToEntityUpper2Line(Object obj) throws Exception {
        MapUtil.mapKeySetUpper2Line(this);
         thisToEntity(obj);
    }

    public void  thisToEntityLine2Upper(Object obj) throws Exception {
        MapUtil.mapKeySetLine2Upper( this);
         thisToEntity(obj);
    }



    public void thisToEntity(Object toObj) throws Exception {

        Map2JavaBeanUtil.transMap2Bean(this,toObj);
    }

    public <T> T thisToEntity(Class<T> toClass) throws Exception {

        return (T) Map2JavaBeanUtil.transMap2Bean(this,toClass);
    }

    public <T> T thisToEntityUpper2Line(Class<T> toClass) throws Exception {
//        this.keySet()
        MapUtil.mapKeySetUpper2Line(this);
        return thisToEntity(toClass);
    }

    public <T> T thisToEntityLine2Upper(Class<T> toClass) throws Exception {
        MapUtil.mapKeySetLine2Upper( this);
        return thisToEntity(toClass);
    }


    public P(Map map) {
        super(map);
    }



    public void initPageArg(){
        if (this.get("page") == null) {
            this.put("page",0);
        }
        if (this.get("limit") == null) {
            this.put("limit",10);
        }
    }



    public void batchToInt(String... names){
        for (String name : names) {
            String s = (String) this.get(name);
            if ( s!= null) {
                this.put(name,Integer.parseInt(s));
            }
        }
    }


    public String getString(String key) {
        Object value =   this.get(key);
        if (value == null) {
            return null;
        }
        return String.valueOf(value);
    }

    public Integer getInt(String key) {
        String value =  (String) this.get(key);
        if (value == null) {
            return null;
        }
        return Integer.parseInt(value);
    }

    public Long getLong(String key) {
        String value =  (String) this.get(key);
        if (value == null) {
            return null;
        }
        return Long.parseLong(value);
    }

    public Double getDouble(String key) {
        String value =  (String) this.get(key);
        if (value == null) {
            return null;
        }
        return Double.parseDouble(value);
    }

    public Float getFloat(String key) {
        String value =  (String) this.get(key);
        if (value == null) {
            return null;
        }
        return Float.parseFloat(value);
    }

    public BigDecimal getBigDecimal(String key) {
        String value = (String)  this.get(key);
        if (value == null) {
            return null;
        }
        return new BigDecimal(value);
    }






}
