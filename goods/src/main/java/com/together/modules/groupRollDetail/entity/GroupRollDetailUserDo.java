package com.together.modules.groupRollDetail.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;


public class GroupRollDetailUserDo implements Serializable {

    private static final long serialVersionUID = 1L;

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
     * 优惠价金额
     */
    private Double money;

    /**
     * 店铺地址
     */
    private String shopAddress;

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public String getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 备注
     */
    private String remark;

    public Date getUseTime() {
        return useTime;
    }

    public void setUseTime(Date useTime) {
        this.useTime = useTime;
    }

    public Integer getGdetailId() {
        return gdetailId;
    }

    public GroupRollDetailUserDo setGdetailId(Integer gdetailId) {
        this.gdetailId = gdetailId;
        return this;
    }
    public Integer getUserId() {
        return userId;
    }

    public GroupRollDetailUserDo setUserId(Integer userId) {
        this.userId = userId;
        return this;
    }
    public Integer getGid() {
        return gid;
    }

    public GroupRollDetailUserDo setGid(Integer gid) {
        this.gid = gid;
        return this;
    }
    public Integer getStatus() {
        return status;
    }

    public GroupRollDetailUserDo setStatus(Integer status) {
        this.status = status;
        return this;
    }
    public Date getAddTime() {
        return addTime;
    }

    public GroupRollDetailUserDo setAddTime(Date addTime) {
        this.addTime = addTime;
        return this;
    }
    public Date getExpirationTime() {
        return expirationTime;
    }

    public GroupRollDetailUserDo setExpirationTime(Date expirationTime) {
        this.expirationTime = expirationTime;
        return this;
    }

    @Override
    public String toString() {
        return "GroupRollDetailEntity{" +
            "gdetailId=" + gdetailId +
            ", userId=" + userId +
            ", gid=" + gid +
            ", status=" + status +
            ", addTime=" + addTime +
            ", expirationTime=" + expirationTime +
        "}";
    }
}
