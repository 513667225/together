package com.together.enun;


/**
 * 拼团次数枚举
 * @author  Agu
 */
public enum TogetherNumber {

    ONCE(1), FIVE(5), TEN(10), INFINITI(-1);


    private int number;


    public  static TogetherNumber forNumber(int i){

        for (TogetherNumber value : TogetherNumber.values()) {
            if (i==value.number){
                return value;
            }
        }


        return null;
    }


    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

     TogetherNumber(int number) {
        this.number = number;
    }


}
