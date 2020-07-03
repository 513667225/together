package com.together.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * 入参操作类:P
 * @author Agu
 */
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

    public P(Map map) {
        super(map);
    }

    /**
     *  参考 @see R  里面的同方法
     *  区别： 此方法是将自己本身转化
     * @param obj
     * @throws Exception
     */
    public void thisToEntityUpper2Line(Object obj) throws Exception {
        MapUtil.mapKeySetUpper2Line(this);
        thisToEntity(obj);
    }

    /**
     *  参考 @see R  里面的同方法
     *  区别： 此方法是将自己本身转化
     * @param obj
     * @throws Exception
     */
    public void  thisToEntityLine2Upper(Object obj) throws Exception {
        MapUtil.mapKeySetLine2Upper( this);
        thisToEntity(obj);
    }

    /**
     *  参考 @see R  里面的同方法
     *  区别： 此方法是将自己本身转化
     * @param toClass
     * @throws Exception
     */
    public <T> T thisToEntityUpper2Line(Class<T> toClass) throws Exception {
//        this.keySet()
        MapUtil.mapKeySetUpper2Line(this);
        return thisToEntity(toClass);
    }

    /**
     *  参考 @see R  里面的同方法
     *  区别： 此方法是将自己本身转化
     * @param toClass
     * @throws Exception
     */
    public <T> T thisToEntityLine2Upper(Class<T> toClass) throws Exception {
        MapUtil.mapKeySetLine2Upper( this);
        return thisToEntity(toClass);
    }


    /**
     *  参考 @see R  里面的同方法
     *  区别： 此方法是将自己本身转化
     * @param toObj
     * @throws Exception
     */
    public void thisToEntity(Object toObj) throws Exception {

        Map2JavaBeanUtil.transMap2Bean(this,toObj);
    }

    /**
     *  参考 @see R  里面的同方法
     *  区别： 此方法是将自己本身转化
     * @param toClass
     * @throws Exception
     */
    public <T> T thisToEntity(Class<T> toClass) throws Exception {

        return (T) Map2JavaBeanUtil.transMap2Bean(this,toClass);
    }


    /**
     * 初始化分页参数 : 当需要分页的接口，但是需要给为空的分页参数一个默认值时可以调用此方法
     * page:0 & limit:10 是mybatis plus  Page 当中的默认值
     */
    public void initPageArg(){
        if (this.get("page") == null) {
            this.put("page",0);
        }
        if (this.get("limit") == null) {
            this.put("limit",10);
        }
    }


    /**
     * 批量将一些key转化为int类型
     * @param names
     */
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
