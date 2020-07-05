package com.together.enun;

import com.together.parameter.ReMoney;

/**
 * 拼团返现档次对应的钱
 *@author  Agu
 */
public enum GoodsLevel {

    ONE(1),TOW(2),THREE(3),FOUR(4);


    private int reward;

    private  double directPush;
    private  double indirectPush;

    public double getDirectPush() {
        return directPush;
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
     }

    public  static GoodsLevel forNumber(int i){
        for (GoodsLevel value : GoodsLevel.values()) {
            if (i==value.reward){
                return value;
            }
        }
        return null;
    }


}
