package com.together.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.HashMap;

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


    public <T> T thisToEntity(Class<T> toClass) throws Exception {

       return (T) Map2JavaBeanUtil.transMap2Bean(this,toClass);
    }

    public void thisToEntity(Object toObj) throws Exception {

        Map2JavaBeanUtil.transMap2Bean(this,toObj);
    }


    public void batchToInt(String... names){
        for (String name : names) {
            String s = (String) this.get(name);
            if ( s!= null) {
                this.put(name,Integer.parseInt(s));
            }
        }
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
