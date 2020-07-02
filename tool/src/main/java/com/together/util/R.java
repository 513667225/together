package com.together.util;

import java.util.HashMap;
import java.util.Map;

public class R extends HashMap {
    public static String SUCCESS_CODE = "0";
    public static String ERROR_CODE = "500";
    public static String DATA_KEY = "data";
    public static String MSG_KEY = "msg";

    private R() {

    }

    public R set(String key, Object object) {
        super.put(key, object);
        return this;
    }

//    transUnderLine2Upper


    public void thisToEntityUpper2Line(Object obj) throws Exception {
        MapUtil.mapKeySetUpper2Line(this);
        thisToEntity(obj);
    }

    public void  thisToEntityLine2Upper(Object obj) throws Exception {
        MapUtil.mapKeySetLine2Upper( this);
        thisToEntity(obj);
    }


    public <T> T thisToEntityUpper2Line(Class<T> toClass) throws Exception {
//        this.keySet()
        MapUtil.mapKeySetUpper2Line((Map) this.get("data"));
        return thisToEntity(toClass);
    }

    public <T> T thisToEntityLine2Upper(Class<T> toClass) throws Exception {
        MapUtil.mapKeySetLine2Upper((Map) this.get("data"));
        return thisToEntity(toClass);
    }

    public <T> T thisToEntity(Class<T> toClass) throws Exception {

        return (T) Map2JavaBeanUtil.transMap2Bean((Map) this.get("data"), toClass);
    }


    public void thisToEntity(Object toObj) throws Exception {

        Map2JavaBeanUtil.transMap2Bean((Map) this.get("data"), toObj);
    }


    public static R ok() {
        return new R();
    }

    public static R success() {

        return R.ok().set("code", R.SUCCESS_CODE).set(R.MSG_KEY, "操作成功");
    }

    public static R success(String msg) {

        return R.ok().set("code", R.SUCCESS_CODE).set(R.MSG_KEY, msg);
    }

    public static R success(String msg, Object object) {

        return R.ok().set("code", R.SUCCESS_CODE).set(R.MSG_KEY, msg).set(R.DATA_KEY, object);
    }

    public R data(Object obj) {
        return this.set("data", obj);
    }

    public static R error() {
        return R.ok().set(R.MSG_KEY, "操作失败").set("code", R.ERROR_CODE);
    }

    public static R error(String msg) {
        return R.ok().set(R.MSG_KEY, msg).set("code", R.ERROR_CODE);
    }

    public static R error(String msg, Object object) {
        return R.ok().set(R.MSG_KEY, msg).set(R.DATA_KEY, object).set("code", R.ERROR_CODE);
    }

}
