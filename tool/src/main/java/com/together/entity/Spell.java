package com.together.entity;

import com.together.enun.GoodsLevel;
import com.together.enun.TogetherNumber;

public class Spell {

    //拼团的用户ID
    private  int userId;
    //拼团的商品ID
    private  int goodsId;
    //属于的团号
    private String group;
    //总共拼团次数
    private TogetherNumber togetherNumber;
    //拼团商品的档次
    private GoodsLevel goodsLevel;
    //已经拼团的次数
    private int gameCount = 0;
    //省(市)代理ID
    private int admin_id;
    //商品价格
    private  double goods_price;
    //是否为拼团卷
    private  boolean isCoupons;


    public Spell(int userId, int goodsId, TogetherNumber togetherNumber, GoodsLevel goodsLevel,int admin_id,double goods_price,boolean isCoupons) {
        this.userId = userId;
        this.goodsId = goodsId;
//        this.group = group;
        this.togetherNumber = togetherNumber;
        this.goodsLevel = goodsLevel;
        this.admin_id = admin_id;
        this.goods_price = goods_price;
        this.isCoupons = isCoupons;
//        this.gameCount = gameCount;
    }


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public TogetherNumber getTogetherNumber() {
        return togetherNumber;
    }

    public void setTogetherNumber(TogetherNumber togetherNumber) {
        this.togetherNumber = togetherNumber;
    }

    public GoodsLevel getGoodsLevel() {
        return goodsLevel;
    }

    public void setGoodsLevel(GoodsLevel goodsLevel) {
        this.goodsLevel = goodsLevel;
    }

    public int getGameCount() {
        return gameCount;
    }

    public void setGameCount(int gameCount) {
        this.gameCount = gameCount;
    }

    public int getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(int admin_id) {
        this.admin_id = admin_id;
    }

    public double getGoods_price() {
        return goods_price;
    }

    public boolean isCoupons() {
        return isCoupons;
    }
}
