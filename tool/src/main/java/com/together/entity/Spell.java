package com.together.entity;

import com.together.enun.TogetherNumber;

public class Spell {


    private  int userId;

    private  int goodsId;

    private String group;

    private TogetherNumber togetherNumber;

    private int gameCount = 0;


    public String getGroup() {

        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public Spell() {
    }


    public Integer getGameCount() {
        return gameCount;
    }

    public void setGameCount(Integer gameCount) {
        this.gameCount = gameCount;
    }

    public Spell(Integer userId, Integer goodsId, TogetherNumber togetherNumber) {
        this.userId = userId;
        this.goodsId = goodsId;
        this.togetherNumber = togetherNumber;
    }


    public TogetherNumber getTogetherNumber() {
        return togetherNumber;
    }

    public void setTogetherNumber(TogetherNumber togetherNumber) {
        this.togetherNumber = togetherNumber;
    }


    public Integer getUserId() {
        return userId;
    }


    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }


}
