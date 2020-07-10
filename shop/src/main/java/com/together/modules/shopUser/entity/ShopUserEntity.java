package com.together.modules.shopUser.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author 
 * @since 2020-07-02
 */
@TableName("shop_user")
public class ShopUserEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "shopuser_id",type = IdType.AUTO)
    private Integer shopuserId;

    /**
     * 商家后台登录账号
     */
    private String shopuserName;

    /**
     * 商家后台登录密码
     */
    private String shopuserPassword;

    /**
     * 创建时间
     */
    private Date addTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    public Integer getShopuserId() {
        return shopuserId;
    }

    public ShopUserEntity setShopuserId(Integer shopuserId) {
        this.shopuserId = shopuserId;
        return this;
    }
    public String getShopuserName() {
        return shopuserName;
    }

    public ShopUserEntity setShopuserName(String shopuserName) {
        this.shopuserName = shopuserName;
        return this;
    }
    public String getShopuserPassword() {
        return shopuserPassword;
    }

    public ShopUserEntity setShopuserPassword(String shopuserPassword) {
        this.shopuserPassword = shopuserPassword;
        return this;
    }
    public Date getAddTime() {
        return addTime;
    }

    public ShopUserEntity setAddTime(Date addTime) {
        this.addTime = addTime;
        return this;
    }
    public Date getUpdateTime() {
        return updateTime;
    }

    public ShopUserEntity setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    @Override
    public String toString() {
        return "ShopUserEntity{" +
            "shopuserId=" + shopuserId +
            ", shopuserName=" + shopuserName +
            ", shopuserPassword=" + shopuserPassword +
            ", addTime=" + addTime +
            ", updateTime=" + updateTime +
        "}";
    }
}
