package com.together.modules.shop.entity;

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
 * @since 2020-06-28
 */
@TableName("shop")
public class ShopEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "shop_id",type = IdType.AUTO)
    private Integer shopId;

    /**
     * 商家名
     */
    private String shopName;

    /**
     * 公告语
     */
    private String shopSlogan;

    /**
     * 详情介绍
     */
    private String shopDetail;

    /**
     * 商家头像
     */
    private String shopIcon;

    /**
     * 商家店面图片
     */
    private String shopPic;

    /**
     * 商家品类
     */
    private String shopCategory;

    /**
     * 商家省份
     */
    private String shopAddress;

    /**
     * 商家城市
     */
    private String city;

    /**
     * 商家城市
     */
    private String area;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    /**
     * 商家电话
     */
    private String shopTel;

    /**
     * 营业时间
     */
    private String shopTime;

    /**
     * 商家资质ID
     */
    private Integer aptitudeId;

    /**
     * 创建时间
     */
    private Date addTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 地区代理管理员
     */
    private Integer adminId;

    private Integer shopType;

    private Integer shopuserId;


    public Integer getShopuserId() {
        return shopuserId;
    }

    public void setShopuserId(Integer shopuserId) {
        this.shopuserId = shopuserId;
    }

    public Integer getShopType() {
        return shopType;
    }

    public void setShopType(Integer shopType) {
        this.shopType = shopType;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }


    public Integer getShopId() {
        return shopId;
    }

    public ShopEntity setShopId(Integer shopId) {
        this.shopId = shopId;
        return this;
    }
    public String getShopName() {
        return shopName;
    }

    public ShopEntity setShopName(String shopName) {
        this.shopName = shopName;
        return this;
    }
    public String getShopSlogan() {
        return shopSlogan;
    }

    public ShopEntity setShopSlogan(String shopSlogan) {
        this.shopSlogan = shopSlogan;
        return this;
    }
    public String getShopDetail() {
        return shopDetail;
    }

    public ShopEntity setShopDetail(String shopDetail) {
        this.shopDetail = shopDetail;
        return this;
    }
    public String getShopIcon() {
        return shopIcon;
    }

    public ShopEntity setShopIcon(String shopIcon) {
        this.shopIcon = shopIcon;
        return this;
    }
    public String getShopPic() {
        return shopPic;
    }

    public ShopEntity setShopPic(String shopPic) {
        this.shopPic = shopPic;
        return this;
    }
    public String getShopCategory() {
        return shopCategory;
    }

    public ShopEntity setShopCategory(String shopCategory) {
        this.shopCategory = shopCategory;
        return this;
    }
    public String getShopAddress() {
        return shopAddress;
    }

    public ShopEntity setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
        return this;
    }
    public String getShopTel() {
        return shopTel;
    }

    public ShopEntity setShopTel(String shopTel) {
        this.shopTel = shopTel;
        return this;
    }
    public String getShopTime() {
        return shopTime;
    }

    public ShopEntity setShopTime(String shopTime) {
        this.shopTime = shopTime;
        return this;
    }
    public Integer getAptitudeId() {
        return aptitudeId;
    }

    public ShopEntity setAptitudeId(Integer aptitudeId) {
        this.aptitudeId = aptitudeId;
        return this;
    }
    public Date getAddTime() {
        return addTime;
    }

    public ShopEntity setAddTime(Date addTime) {
        this.addTime = addTime;
        return this;
    }
    public Date getUpdateTime() {
        return updateTime;
    }

    public ShopEntity setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    @Override
    public String toString() {
        return "ShopEntity{" +
            "shopId=" + shopId +
            ", shopName=" + shopName +
            ", shopSlogan=" + shopSlogan +
            ", shopDetail=" + shopDetail +
            ", shopIcon=" + shopIcon +
            ", shopPic=" + shopPic +
            ", shopCategory=" + shopCategory +
            ", shopAddress=" + shopAddress +
            ", shopTel=" + shopTel +
            ", shopTime=" + shopTime +
            ", aptitudeId=" + aptitudeId +
            ", addTime=" + addTime +
            ", updateTime=" + updateTime +
            ", shopType=" + shopType +
            ", city=" + city +
            ", area=" + area +
            ", shopuserId=" + shopuserId +
            ", adminId=" + adminId +


        "}";
    }
}
