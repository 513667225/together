package com.together.util.utli;

/**
 * 提示信息
 * Created by xm on 2018/11/22 0022.
 */
public final class TipMsgConstant {

    /**
     * 1001 : 积分不足
     * 1002 : 积分已过期
     * 1003 : 积分已被抢完
     * 1004 ： 你已领取
     * 1005 : 没有匹配到店铺
     * 1006 : 没有对应的商品
     * 1007 : 没有该用户
     * 1008 : 免费体验已用完
     * 1009 : 操作失败
     * 1010 : 参数不合法
     * 1011 : 请绑定手机号
     * 1012 ：没有明细
     * 1013 : 该用户是会员
     * 1014 ： 已绑定两个副卡
     * 1015 ： 店铺距离太远
     * 1016 ：手机号已被绑定
     * 1017 : 订单已被抢
     * 1018 ： 订单已取消
     * 1019 ： 您没有订单可接
     * 1020 : 没有移动店铺接单
     * 1021 ： 没有支付的账单
     * 1022 : 暂无小票
     * 1023 ： 加载完毕
     * 1024 : 没有该订单
     * 1025 : 服务器异常
     * 1026 : 没有筛选要求
     * 1027 : cityCode无效
     * 1028 : 没有查询到区
     * 1029 ：没有查询到街道
     * 1030 : 没有房源
     * 1031 ： 平台已确认
     * 1032 ： 合同已生效
     * 1033 ： 等待平台确认
     * 1034 ： 待房东支付
     * 1035 ： 余额不足
     */


    /**
     * 提示常量
     */
    public static class msg{
        public static final String LACK_OF_INTEGRAL = "积分不足";
        public static final String INTEGRAL_ENDTIME = "积分已过期";
        public static final String INTEGRAL_ROB = "积分已被抢完";
        public static final String GET_INTEGRAL = "你已领取";
        public static final String NO_CORRESPONDING_STORE = "没有匹配到店铺";
        public static final String NO_COMMODITY = "没有对应的商品";
        public static final String NO_USER = "没有该用户";
        public static final String USER_NO_MEMBER = "免费体验已用完";
        public static final String OPERATION_FAILURE = "操作失败";
        public static final String FAIL = "参数不合法";
        public static final String USER_TAKE_PHONE = "请绑定手机号";
        public static final String NO_DETAIL = "没有明细";
        public static final String USER_IS_MEMBER = "该用户是会员";
        public static final String USER_IS_TWO_MEMBER = "已绑定两个副卡";
        public static final String STORE_LONG = "店铺距离太远";
        public static final String USER_IS_PHONE = "手机号已被绑定";
        public static final String ORDER__BE_ROB = "订单已被抢";
        public static final String ORDER_CANCEL = "订单已取消";
        public static final String NO_ORDER_JOIN = "您没有订单可接";
        public static final String NO_MOVE_JOIN_ORDER = "没有移动店铺接单";
        public static final String NO_PAY_ORDER = "没有支付的账单";
        public static final String NO_RECEIPT = "暂无小票";
        public static final String LOAD_FINISH= "加载完毕";
        public static final String NO_ORDER= "没有该订单";
        public static final String SERVER_EXCEPTION= "服务器异常";
        public static final String SCREENING_REQUIRE= "没有该筛选要求";
        public static final String CITY_CODE_INEFFICIENCY= "cityCode无效";
        public static final String COUNTY_INEFFICIENCY= "没有查询到区";
        public static final String TOWN_INEFFICIENCY= "没有查询到街道";
        public static final String NO_HOUSE= "没有房源";
        public static final String CONTRACT_AFFIRM= "平台已确认";
        public static final String CONTRACT_EFFECT= "合同已生效";
        public static final String WAIT_PLATFORM_AFFIRM= "等待平台确认";
        public static final String WAIT_LANDLORD_PAY= "待房东支付";
        public static final String NOT_SUFFICIENT_FUNDS= "余额不足";
    }


    /**
     * 转态码常量
     */
    public static class codeConstant {
        public static final int LACK_OF_INTEGRAL = 1001;
        public static final int INTEGRAL_ENDTIME = 1002;
        public static final int INTEGRAL_ROB = 1003;
        public static final int GET_INTEGRAL = 1004;
        public static final int NO_CORRESPONDING_STORE = 1005;
        public static final int NO_COMMODITY = 1006;
        public static final int NO_USER = 1007;
        public static final int USER_NO_MEMBER = 1008;
        public static final int OPERATION_FAILURE = 1009;
        public static final int FAIL = 1010;
        public static final int USER_TAKE_PHONE = 1011;
        public static final int NO_DETAIL = 1012;
        public static final int USER_IS_MEMBER = 1013;
        public static final int USER_IS_TWO_MEMBER = 1014;
        public static final int STORE_LONG = 1015;
        public static final int USER_IS_PHONE = 1016;
        public static final int ORDER__BE_ROB = 1017;
        public static final int ORDER_CANCEL = 1018;
        public static final int NO_ORDER_JOIN = 1019;
        public static final int NO_MOVE_JOIN_ORDER = 1020;
        public static final int NO_PAY_ORDER = 1021;
        public static final int NO_RECEIPT = 1022;
        public static final int LOAD_FINISH= 1023;
        public static final int NO_ORDER= 1024;
        public static final int SERVER_EXCEPTION= 1025;
        public static final int SCREENING_REQUIRE= 1026;
        public static final int CITY_CODE_INEFFICIENCY= 1027;
        public static final int COUNTY_INEFFICIENCY= 1028;
        public static final int TOWN_INEFFICIENCY= 1029;
        public static final int NO_HOUSE= 1030;
        public static final int CONTRACT_AFFIRM= 1031;
        public static final int CONTRACT_EFFECT= 1032;
        public static final int WAIT_PLATFORM_AFFIRM= 1033;
        public static final int WAIT_LANDLORD_PAY= 1034;
        public static final int NOT_SUFFICIENT_FUNDS= 1035;
    }


}
