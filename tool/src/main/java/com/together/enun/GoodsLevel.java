package com.together.enun;

/**
 * 拼团返现档次对应的钱
 *@author  Agu
 */
public enum GoodsLevel {

    ONE(1),TOW(2),THREE(3),FOUR(4);


    private int reward;

    public int getReward() {
        return reward;
    }

    public void setReward(int reward) {
        this.reward = reward;
    }

    GoodsLevel(int reward){
        this.reward = reward;
     }


}
