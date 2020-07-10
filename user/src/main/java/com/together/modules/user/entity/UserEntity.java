package com.together.modules.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author 
 * @since 2020-06-28
 */
@TableName("user")
public class UserEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "user_id",type= IdType.AUTO)
    private Integer userId;


    /**
     * 用户密码
     */
    private String userPassword;

    /**
     * 性别,0 未知;1 男;2 女
     */
    private Integer userGender;

    /**
     * 生日
     */
    private Date userBirthday;

    /**
     * 最近一次登录时间
     */
    private Date userLastLoginTime;

    /**
     * 最近一次登录IP地址
     */
    private String userLastLoginIp;

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
     * 微信登录openid
     */
    private String weixinOpenid;


    /**
     * 0 可用;1 禁用;2 注销
     */
    private Integer userStatus;

    /**
     * 用户推荐人ID
     */
    private Integer userReferrer;

    /**
     * 创建时间
     */
    private Date addTime;

    /**
     * 更新时间
     */
    private Date updateTime;


    /**
     * 团队人数
     */
    private int goupSize;

    /**
     * 直邀人数
     */
    private int underlingSize;


    //顶层邀请人
    private Integer topRefereeId;

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

    public Integer getTopRefereeId() {
        return topRefereeId;
    }

    public void setTopRefereeId(Integer topRefereeId) {
        this.topRefereeId = topRefereeId;
    }

    public int getGoupSize() {
        return goupSize;
    }

    public void setGoupSize(int goupSize) {
        this.goupSize = goupSize;
    }

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

    private  int decline_count;

    private  int decline_number;


    public int getDecline_count() {
        return decline_count;
    }

    public void setDecline_count(int decline_count) {
        this.decline_count = decline_count;
    }

    public int getDecline_number() {
        return decline_number;
    }

    public void setDecline_number(int decline_number) {
        this.decline_number = decline_number;
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

    public Integer getUserId() {
        return userId;
    }

    public UserEntity setUserId(Integer userId) {
        this.userId = userId;
        return this;
    }
    public String getUserPassword() {
        return userPassword;
    }

    public UserEntity setUserPassword(String userPassword) {
        this.userPassword = userPassword;
        return this;
    }
    public Integer getUserGender() {
        return userGender;
    }

    public UserEntity setUserGender(Integer userGender) {
        this.userGender = userGender;
        return this;
    }
    public Date getUserBirthday() {
        return userBirthday;
    }

    public UserEntity setUserBirthday(Date userBirthday) {
        this.userBirthday = userBirthday;
        return this;
    }
    public Date getUserLastLoginTime() {
        return userLastLoginTime;
    }

    public UserEntity setUserLastLoginTime(Date userLastLoginTime) {
        this.userLastLoginTime = userLastLoginTime;
        return this;
    }
    public String getUserLastLoginIp() {
        return userLastLoginIp;
    }

    public UserEntity setUserLastLoginIp(String userLastLoginIp) {
        this.userLastLoginIp = userLastLoginIp;
        return this;
    }
    public Integer getUserLevel() {
        return userLevel;
    }

    public UserEntity setUserLevel(Integer userLevel) {
        this.userLevel = userLevel;
        return this;
    }
    public String getUserNickname() {
        return userNickname;
    }

    public UserEntity setUserNickname(String userNickname) {
        this.userNickname = userNickname;
        return this;
    }
    public String getUserMobile() {
        return userMobile;
    }

    public UserEntity setUserMobile(String userMobile) {
        this.userMobile = userMobile;
        return this;
    }
    public String getUserAvatar() {
        return userAvatar;
    }

    public UserEntity setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
        return this;
    }
    public String getWeixinOpenid() {
        return weixinOpenid;
    }

    public UserEntity setWeixinOpenid(String weixinOpenid) {
        this.weixinOpenid = weixinOpenid;
        return this;
    }
    public Integer getUserStatus() {
        return userStatus;
    }

    public UserEntity setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
        return this;
    }
    public Integer getUserReferrer() {
        return userReferrer;
    }

    public UserEntity setUserReferrer(Integer userReferrer) {
        this.userReferrer = userReferrer;
        return this;
    }
    public Date getAddTime() {
        return addTime;
    }

    public UserEntity setAddTime(Date addTime) {
        this.addTime = addTime;
        return this;
    }
    public Date getUpdateTime() {
        return updateTime;
    }

    public UserEntity setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
            "userId=" + userId +
            ", userPassword=" + userPassword +
            ", userGender=" + userGender +
            ", userBirthday=" + userBirthday +
            ", userLastLoginTime=" + userLastLoginTime +
            ", userLastLoginIp=" + userLastLoginIp +
            ", userLevel=" + userLevel +
            ", userNickname=" + userNickname +
            ", userMobile=" + userMobile +
            ", userAvatar=" + userAvatar +
            ", weixinOpenid=" + weixinOpenid +
            ", userStatus=" + userStatus +
            ", userReferrer=" + userReferrer +
            ", addTime=" + addTime +
            ", updateTime=" + updateTime +
        "}";
    }
}
