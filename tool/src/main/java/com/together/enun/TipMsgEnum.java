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
    PARAMETER_NULL_Excption(1005,"缺少必须参数"),
    UPDATE_MONRY_FAIL(1006,"修改金钱相关失败"),
    INSERT_USER_ADDRESS(1007,"用户地址添加失败"),
    UPDATE_USER_ADDRESS(1008,"用户地址修改失败"),
    DELETE_USER_ADDRESS(1009,"用户地址删除失败"),
    MONEY_MAX(1010,"成本金额大于5折"),
    SHOP_USER_NOT_LOGIN(1011,"商家用户未登录"),
    ADMIN_USER_NOT_LOGIN(1012,"系统用户未登录");

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
