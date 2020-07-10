package com.together.modules.groupRoll.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author 
 * @since 2020-07-06
 */
@TableName("group_roll")
public class GroupRollEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "grouproll_id", type = IdType.AUTO)
    private Integer grouprollId;

    /**
     * 所属店铺
     */
    private Integer shopId;

    /**
     * 添加时间
     */
    private Date addTime;

    /**
     * 优惠价金额
     */
    private Double money;

    /**
     * 店铺收回多少
     */
    private Double shopPublishMoney;

    /**
     * 拼团卷状态，0正常，1已抢（未使用），2，已使用
     */
    private Integer status;

    /**
     * 店铺地址
     */
    private String shopAddress;

    /**
     * 备注
     */
    private String remark;


    /**
     * 库存
     */
    private Integer inventory;

    public Integer getInventory() {
        return inventory;
    }

    public void setInventory(Integer inventory) {
        this.inventory = inventory;
    }

    public Integer getGrouprollId() {
        return grouprollId;
    }

    public GroupRollEntity setGrouprollId(Integer grouprollId) {
        this.grouprollId = grouprollId;
        return this;
    }
    public Integer getShopId() {
        return shopId;
    }

    public GroupRollEntity setShopId(Integer shopId) {
        this.shopId = shopId;
        return this;
    }
    public Date getAddTime() {
        return addTime;
    }

    public GroupRollEntity setAddTime(Date addTime) {
        this.addTime = addTime;
        return this;
    }
    public Double getMoney() {
        return money;
    }

    public GroupRollEntity setMoney(Double money) {
        this.money = money;
        return this;
    }
    public Double getShopPublishMoney() {
        return shopPublishMoney;
    }

    public GroupRollEntity setShopPublishMoney(Double shopPublishMoney) {
        this.shopPublishMoney = shopPublishMoney;
        return this;
    }
    public Integer getStatus() {
        return status;
    }

    public GroupRollEntity setStatus(Integer status) {
        this.status = status;
        return this;
    }
    public String getShopAddress() {
        return shopAddress;
    }

    public GroupRollEntity setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
        return this;
    }
    public String getRemark() {
        return remark;
    }

    public GroupRollEntity setRemark(String remark) {
        this.remark = remark;
        return this;
    }

    @Override
    public String toString() {
        return "GroupRollEntity{" +
            "grouprollId=" + grouprollId +
            ", shopId=" + shopId +
            ", addTime=" + addTime +
            ", money=" + money +
            ", shopPublishMoney=" + shopPublishMoney +
            ", status=" + status +
            ", shopAddress=" + shopAddress +
            ", remark=" + remark +
        "}";
    }
}
