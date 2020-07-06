package com.together.enun;

/**
 * @author Agu
 */
public enum  TipMsgEnum {

    DEFAULT_ERROR_MSG(500,"系统异常"),
    TOKEN_OF_OVERDUE(1001,"Token过期"),
    TOKEN_OF_Excption(1002,"Token异常"),
    TOKEN_CREATE_Excption(1003,"JWT签名失败"),
    TOKEN_NULL_Excption(1004,"无Token参数"),
    PARAMETER_NULL_Excption(1005,"缺少必须参数");

    private  int code;

    private String msg;



    public  static TipMsgEnum forMsg(String msg){
        for (TipMsgEnum value : values()) {
            if (value.getMsg().equals(msg)) {
                return value;
            }
        }
        return DEFAULT_ERROR_MSG;
    }

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