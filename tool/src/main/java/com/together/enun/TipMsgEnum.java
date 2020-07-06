package com.together.enun;

/**
 * @author Agu
 */
public enum  TipMsgEnum {

    DEFAULT_ERROR_MSG(500,"系统异常");


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
