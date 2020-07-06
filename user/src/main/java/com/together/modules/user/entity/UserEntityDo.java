package com.together.modules.user.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;


public class UserEntityDo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer userId;

    /**
     * 0 普通用户;1 高级会员;2 服务经理;3 总监
     */
    private Integer userLevel;

    /**
     * 用户昵称
     */
    private String userNickname;

    /**
     * 用户手机号码
     */
    private String userMobile;

    /**
     * 用户头像图片
     */
    private String userAvatar;

    /**
     * 用户推荐人ID
     */
    private Integer userReferrer;


    /**
     * 团队人数
     */
    private int goupSize;

    /**
     * 直邀人数
     */
    private int underlingSize;

    /**
     * 下辖会员人数
     */
    private int memberSize;

    /**
     * 下辖服务经理人数
     */
    private int managerSize;

    /**
     * 下辖总监人数
     */
    private int majordomoSize;

    /**
     * 团队服务经理人数
     */
    private int teammanagerSize;


    //购物金
    private double shopping_gold;

    //积分
    private double integral;

    //拼豆
    private double spell_bean;

    //余额
    private double balance;

    public double getShopping_gold() {
        return shopping_gold;
    }

    public void setShopping_gold(double shopping_gold) {
        this.shopping_gold = shopping_gold;
    }

    public double getIntegral() {
        return integral;
    }

    public void setIntegral(double integral) {
        this.integral = integral;
    }

    public double getSpell_bean() {
        return spell_bean;
    }

    public void setSpell_bean(double spell_bean) {
        this.spell_bean = spell_bean;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(Integer userLevel) {
        this.userLevel = userLevel;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public Integer getUserReferrer() {
        return userReferrer;
    }

    public void setUserReferrer(Integer userReferrer) {
        this.userReferrer = userReferrer;
    }

    public int getGoupSize() {
        return goupSize;
    }

    public void setGoupSize(int goupSize) {
        this.goupSize = goupSize;
    }

    public int getUnderlingSize() {
        return underlingSize;
    }

    public void setUnderlingSize(int underlingSize) {
        this.underlingSize = underlingSize;
    }

    public int getMemberSize() {
        return memberSize;
    }

    public void setMemberSize(int memberSize) {
        this.memberSize = memberSize;
    }

    public int getManagerSize() {
        return managerSize;
    }

    public void setManagerSize(int managerSize) {
        this.managerSize = managerSize;
    }

    public int getMajordomoSize() {
        return majordomoSize;
    }

    public void setMajordomoSize(int majordomoSize) {
        this.majordomoSize = majordomoSize;
    }

    public int getTeammanagerSize() {
        return teammanagerSize;
    }

    public void setTeammanagerSize(int teammanagerSize) {
        this.teammanagerSize = teammanagerSize;
    }
}
