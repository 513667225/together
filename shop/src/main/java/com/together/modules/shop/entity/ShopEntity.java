package com.together.modules.shop.entity;

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
@TableName("shop")
public class ShopEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 店铺ID
     */
    private String shopId;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 店铺名
     */
    private String shopName;

    /**
     * 店铺图片
     */
    private String shopImg;

    public String getShopId() {
        return shopId;
    }

    public ShopEntity setShopId(String shopId) {
        this.shopId = shopId;
        return this;
    }
    public String getUserId() {
        return userId;
    }

    public ShopEntity setUserId(String userId) {
        this.userId = userId;
        return this;
    }
    public String getShopName() {
        return shopName;
    }

    public ShopEntity setShopName(String shopName) {
        this.shopName = shopName;
        return this;
    }
    public String getShopImg() {
        return shopImg;
    }

    public ShopEntity setShopImg(String shopImg) {
        this.shopImg = shopImg;
        return this;
    }

    @Override
    public String toString() {
        return "ShopEntity{" +
            "shopId=" + shopId +
            ", userId=" + userId +
            ", shopName=" + shopName +
            ", shopImg=" + shopImg +
        "}";
    }
}
