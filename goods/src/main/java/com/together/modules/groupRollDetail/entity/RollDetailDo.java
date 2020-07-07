package com.together.modules.groupRollDetail.entity;

import java.util.Date;

public class RollDetailDo {

    private Integer gdetailId;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 拼团卷id
     */
    private Integer gid;

    /**
     * 拼团卷状态，1拼团中，2未使用，3，已使用，4，未拼中，5取消
     */
    private Integer status;

    /**
     * 抢到时间
     */
    private Date addTime;

    /**
     * 过期时间
     */
    private Date expirationTime;

    /**
     * 使用时间
     */
    private Date useTime;


    /**
     * 用户名
     */
    private String username;



    /**
     * 用户手机号
     */
    private String userphone;


    public Integer getGdetailId() {
        return gdetailId;
    }

    public void setGdetailId(Integer gdetailId) {
        this.gdetailId = gdetailId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(Date expirationTime) {
        this.expirationTime = expirationTime;
    }

    public Date getUseTime() {
        return useTime;
    }

    public void setUseTime(Date useTime) {
        this.useTime = useTime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserphone() {
        return userphone;
    }

    public void setUserphone(String userphone) {
        this.userphone = userphone;
    }
}
