package com.together.enun;

import java.math.BigDecimal;

/**
 * @author Agu
 */
public enum DeclineEnum {

    //1~10 1.5  11~20 1.3

    ONE(1,10,new BigDecimal("1.5")),TOW(11,20,new BigDecimal("1.3")),THREE(21,30,new BigDecimal("1.2")),FOUR(31,Integer.MAX_VALUE,new BigDecimal("1.0"));

    int start;

    int end;

    BigDecimal multiple;

    DeclineEnum(int start, int end, BigDecimal multiple) {
        this.start = start;
        this.end = end;
        this.multiple = multiple;
    }

    public  static BigDecimal getMultiple(int count){

        for (DeclineEnum value : values()) {
            int start = value.getStart();
            int end = value.getEnd();
            if (start<=count&&count<=end){
                return value.getMultiple();
            }
        }
        return null;
    }

    public static BigDecimal getMultipleMoney(BigDecimal multiple,BigDecimal money){
        return (money!=null&&multiple!=null)? multiple.subtract(money):null;
    }




    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public BigDecimal getMultiple() {
        return multiple;
    }

    public void setMultiple(BigDecimal multiple) {
        this.multiple = multiple;
    }
}
