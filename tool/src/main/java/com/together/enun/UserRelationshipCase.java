package com.together.enun;



/**
 * @author Agu
 */
public enum UserRelationshipCase {
    //1经理
    CASE1(0.05),

    //2经理
    CASE2(0.02),

    //2经理1总监
    CASE3(0.02),

    //1经理1总监
    CASE4(0.04),

    //无经理1总监
    CASE5(0.09),

    //总监后总监
    CASE6(0.02);

    double rate;

    public double getRate() {
        return rate;
    }

    UserRelationshipCase(double rate){
            this.rate = rate;
     }


     public double reMoneyNum(GoodsLevel goodsLevel){
         return rate*goodsLevel.getReward();
     }


}
