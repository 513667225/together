package com.together.entity;

import com.together.enun.GoodsLevel;
import com.together.enun.TogetherNumber;

public class Spell {


    private  int userId;

    private  int goodsId;

    private String group;

    private TogetherNumber togetherNumber;

    private GoodsLevel goodsLevel;

    private int gameCount = 0;

    private int admin_id;

    public Spell(int userId, int goodsId, TogetherNumber togetherNumber, GoodsLevel goodsLevel,int admin_id) {
        this.userId = userId;
        this.goodsId = goodsId;
//        this.group = group;
        this.togetherNumber = togetherNumber;
        this.goodsLevel = goodsLevel;
        this.admin_id = admin_id;
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
}
