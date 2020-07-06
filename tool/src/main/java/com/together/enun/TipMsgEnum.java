package com.together.enun;

/**
 * @author Agu
 */
public enum  TipMsgEnum {

    LACK_OF_INTEGRAL(1001,"积分不足");


    private  int code;

    private String msg;


      TipMsgEnum(int code,String msg){
          this.code = code;
          this.msg = msg;
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
