package com.together.modules.user.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author 
 * @since 2020-06-26
 */
@TableName("user")
public class UserEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 用户昵称
     */
    private String userName;

    /**
     * 用户账号
     */
    private String userAccount;

    /**
     * 用户密码
     */
    private String userPassword;

    /**
     * 用户电话
     */
    private String userPhonenum;

    /**
     * VIP ID
     */
    private String vipId;

    /**
     * 用户本金
     */
    private String userPrincipal;

    /**
     * 用户奖金
     */
    private String userBonus;

    /**
     * 用户积分
     */
    private String userIntegral;

    public String getUserId() {
        return userId;
    }

    public UserEntity setUserId(String userId) {
        this.userId = userId;
        return this;
    }
    public String getUserName() {
        return userName;
    }

    public UserEntity setUserName(String userName) {
        this.userName = userName;
        return this;
    }
    public String getUserAccount() {
        return userAccount;
    }

    public UserEntity setUserAccount(String userAccount) {
        this.userAccount = userAccount;
        return this;
    }
    public String getUserPassword() {
        return userPassword;
    }

    public UserEntity setUserPassword(String userPassword) {
        this.userPassword = userPassword;
        return this;
    }
    public String getUserPhonenum() {
        return userPhonenum;
    }

    public UserEntity setUserPhonenum(String userPhonenum) {
        this.userPhonenum = userPhonenum;
        return this;
    }
    public String getVipId() {
        return vipId;
    }

    public UserEntity setVipId(String vipId) {
        this.vipId = vipId;
        return this;
    }
    public String getUserPrincipal() {
        return userPrincipal;
    }

    public UserEntity setUserPrincipal(String userPrincipal) {
        this.userPrincipal = userPrincipal;
        return this;
    }
    public String getUserBonus() {
        return userBonus;
    }

    public UserEntity setUserBonus(String userBonus) {
        this.userBonus = userBonus;
        return this;
    }
    public String getUserIntegral() {
        return userIntegral;
    }

    public UserEntity setUserIntegral(String userIntegral) {
        this.userIntegral = userIntegral;
        return this;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
            "userId=" + userId +
            ", userName=" + userName +
            ", userAccount=" + userAccount +
            ", userPassword=" + userPassword +
            ", userPhonenum=" + userPhonenum +
            ", vipId=" + vipId +
            ", userPrincipal=" + userPrincipal +
            ", userBonus=" + userBonus +
            ", userIntegral=" + userIntegral +
        "}";
    }
}
