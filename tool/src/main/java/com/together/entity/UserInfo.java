package com.together.entity;

import java.util.Date;

public class UserInfo {

    public   static final String USER_COOKIE_KEY = "user_key";

    private Integer userId;

    /**
     * 用户名称
     */
    private String userName;

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
     * 微信登录会话KEY
     */
    private String sessionKey;

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


    private Integer shopId;

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public Integer getUserId() {
        return userId;
    }

    public UserInfo setUserId(Integer userId) {
        this.userId = userId;
        return this;
    }
    public String getUserName() {
        return userName;
    }

    public UserInfo setUserName(String userName) {
        this.userName = userName;
        return this;
    }
    public String getUserPassword() {
        return userPassword;
    }

    public UserInfo setUserPassword(String userPassword) {
        this.userPassword = userPassword;
        return this;
    }
    public Integer getUserGender() {
        return userGender;
    }

    public UserInfo setUserGender(Integer userGender) {
        this.userGender = userGender;
        return this;
    }
    public Date getUserBirthday() {
        return userBirthday;
    }

    public UserInfo setUserBirthday(Date userBirthday) {
        this.userBirthday = userBirthday;
        return this;
    }
    public Date getUserLastLoginTime() {
        return userLastLoginTime;
    }

    public UserInfo setUserLastLoginTime(Date userLastLoginTime) {
        this.userLastLoginTime = userLastLoginTime;
        return this;
    }
    public String getUserLastLoginIp() {
        return userLastLoginIp;
    }

    public UserInfo setUserLastLoginIp(String userLastLoginIp) {
        this.userLastLoginIp = userLastLoginIp;
        return this;
    }
    public Integer getUserLevel() {
        return userLevel;
    }

    public UserInfo setUserLevel(Integer userLevel) {
        this.userLevel = userLevel;
        return this;
    }
    public String getUserNickname() {
        return userNickname;
    }

    public UserInfo setUserNickname(String userNickname) {
        this.userNickname = userNickname;
        return this;
    }
    public String getUserMobile() {
        return userMobile;
    }

    public UserInfo setUserMobile(String userMobile) {
        this.userMobile = userMobile;
        return this;
    }
    public String getUserAvatar() {
        return userAvatar;
    }

    public UserInfo setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
        return this;
    }
    public String getWeixinOpenid() {
        return weixinOpenid;
    }

    public UserInfo setWeixinOpenid(String weixinOpenid) {
        this.weixinOpenid = weixinOpenid;
        return this;
    }
    public String getSessionKey() {
        return sessionKey;
    }

    public UserInfo setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
        return this;
    }
    public Integer getUserStatus() {
        return userStatus;
    }

    public UserInfo setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
        return this;
    }
    public Integer getUserReferrer() {
        return userReferrer;
    }

    public UserInfo setUserReferrer(Integer userReferrer) {
        this.userReferrer = userReferrer;
        return this;
    }
    public Date getAddTime() {
        return addTime;
    }

    public UserInfo setAddTime(Date addTime) {
        this.addTime = addTime;
        return this;
    }
    public Date getUpdateTime() {
        return updateTime;
    }

    public UserInfo setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "userId=" + userId +
                ", userName=" + userName +
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
                ", sessionKey=" + sessionKey +
                ", userStatus=" + userStatus +
                ", userReferrer=" + userReferrer +
                ", addTime=" + addTime +
                ", updateTime=" + updateTime +
                "}";
    }

}
