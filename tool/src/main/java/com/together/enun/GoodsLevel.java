package com.together.enun;

import com.together.parameter.DeclineParameter;
import com.together.parameter.ReMoney;

/**
 * reward 拼团返现档次对应的钱
 * shopOutBonus 省() 代分红
 * theHost 主播分红
 * directPush直(indirectPush间)推 分红
 *@author  Agu
 */
public enum GoodsLevel {

    ONE(1),TOW(2),THREE(3),FOUR(4);


    private int reward;

    private double provinceManage;
//    private  double cityManage;
    private double theHost;


    private  double directPush;
    private  double indirectPush;

    public double getDirectPush() {
        return directPush;
    }

    public  double getBalance(){

        return reward* ReMoney.balanceRate;
    }

    public  double getIntegral(){

        return reward *ReMoney.integralRate;
    }

    public void setDirectPush(double directPush) {
        this.directPush = directPush;
    }

    public double getIndirectPush() {
        return indirectPush;
    }

    public void setIndirectPush(double indirectPush) {
        this.indirectPush = indirectPush;
    }

    public int getReward() {
        return reward;
    }

    public void setReward(int reward) {
        this.reward = reward;
    }

    GoodsLevel(int reward){
        this.reward = reward;
        this.directPush = reward* ReMoney.directPush;
        this.indirectPush = reward* ReMoney.indirectPush;
        this.provinceManage = reward;
        this.theHost = reward/2;
     }

    public  static GoodsLevel forNumber(int i){
        for (GoodsLevel value : GoodsLevel.values()) {
            if (i==value.reward){
                return value;
            }
        }
        return null;
    }


    public double getProvinceManage() {
        return provinceManage;
    }

    public void setProvinceManage(double provinceManage) {
        this.provinceManage = provinceManage;
    }


    public double getTheHost() {
        return theHost;
    }

    public void setTheHost(double theHost) {
        this.theHost = theHost;
    }
}
