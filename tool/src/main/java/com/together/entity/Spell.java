package com.together.entity;

import com.together.enun.GoodsLevel;
import com.together.enun.GoodsLevel;
import com.together.enun.TogetherNumber;
import com.together.enun.TogetherNumber;

import java.math.BigDecimal;

public class Spell {

    //拼团的用户ID
    private  int user_id;
    //拼团的商品ID
    private  int goods_id;
    //属于的团号
    private String group;
    //总共拼团次数
    private TogetherNumber together_number;
    //拼团商品的档次
    private GoodsLevel goods_level;
    //已经拼团的次数
    private int game_count = 0;
    //省(市)代理ID
    private int admin_id;
    //商品价格
    private BigDecimal goods_price;
    //商家本钱
    private  double shop_capital;
    //是否为拼团卷
    private  boolean isCoupons;
    //当前拼团商品的商户ID
    private int shopuser_id;
    //发货地址id
    private  int address_id;

    //用户剩余代售次数
    private  int decline_count;
    //用户总代售次数
    private  int decline_cumber;

    public  Spell(){

    }

    public Spell(int user_id, int goods_id, TogetherNumber together_number, GoodsLevel goods_level, int admin_id, BigDecimal goods_price, boolean isCoupons, int shopuser_id, double shop_capital,int address_id) {
        this.user_id = user_id;
        this.goods_id = goods_id;
//        this.group = group;
        this.together_number = together_number;
        this.goods_level = goods_level;
        this.admin_id = admin_id;
        this.goods_price = goods_price;
        this.isCoupons = isCoupons;
//        this.game_count = game_count;
        this.shopuser_id = shopuser_id;
        this.shop_capital = shop_capital;
        this.address_id = address_id;
    }


    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(int goods_id) {
        this.goods_id = goods_id;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public TogetherNumber getTogether_number() {
        return together_number;
    }

    public void setTogether_number(TogetherNumber together_number) {
        this.together_number = together_number;
    }

    public GoodsLevel getGoods_level() {
        return goods_level;
    }

    public void setGoods_level(GoodsLevel goods_level) {
        this.goods_level = goods_level;
    }

    public int getGame_count() {
        return game_count;
    }

    public void setGame_count(int game_count) {
        this.game_count = game_count;
    }

    public int getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(int admin_id) {
        this.admin_id = admin_id;
    }

    public BigDecimal getGoods_price() {
        return goods_price;
    }

    public boolean getIsCoupons() {
        return isCoupons;
    }

    public int getShopuser_id() {
        return shopuser_id;
    }

    public double getShop_capital() {
        return shop_capital;
    }

    public int getAddress_id() {
        return address_id;
    }


    public void setGoods_price(BigDecimal goods_price) {
        this.goods_price = goods_price;
    }

    public void setShop_capital(double shop_capital) {
        this.shop_capital = shop_capital;
    }

    public void setIsCoupons(boolean coupons) {
        isCoupons = coupons;
    }

    public void setShopuser_id(int shopuser_id) {
        this.shopuser_id = shopuser_id;
    }

    public void setAddress_id(int address_id) {
        this.address_id = address_id;
    }

    public int getDecline_count() {
        return decline_count;
    }

    public void setDecline_count(int decline_count) {
        this.decline_count = decline_count;
    }

    public int getDecline_cumber() {
        return decline_cumber;
    }

    public void setDecline_cumber(int decline_cumber) {
        this.decline_cumber = decline_cumber;
    }
}
