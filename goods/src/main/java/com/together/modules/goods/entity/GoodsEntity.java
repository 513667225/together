package com.together.modules.goods.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 商品基本信息表
 * </p>
 *
 * @author 
 * @since 2020-06-28
 */
@TableName("goods")
public class GoodsEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "goods_id", type = IdType.AUTO)
    private Integer goodsId;

    /**
     * 商品编号
     */
    private String goodsSn;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 商品所属类目ID
     */
    private Integer categoryId;

    /**
     * 商家ID
     */
    private Integer shopId;

    /**
     * 商品宣传图片列表，采用JSON数组格式
     */
    private String goodsGallery;

    /**
     * 商品关键字，采用逗号间隔
     */
    private String goodsKeywords;

    /**
     * 商品价格
     */
    private BigDecimal goodsPrice;

    /**
     * 商品简介
     */
    private String goodsBrief;

    /**
     * 是否上架
     */
    private Boolean isOnSale;

    /**
     * 排序
     */
    private Integer sortOrder;

    /**
     * 商品页面商品图片
     */
    private String picUrl;

    /**
     * 商品单位，例如件、盒
     */
    private String goodsUnit;

    /**
     * 商品详细介绍，是富文本格式
     */
    private String goodsDetail;

    /**
     * 主播ID
     */
    private Integer spokesmanId;

    /**
     * 创建时间
     */
    private Date addTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    public Integer getGoodsId() {
        return goodsId;
    }

    public GoodsEntity setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
        return this;
    }
    public String getGoodsSn() {
        return goodsSn;
    }

    public GoodsEntity setGoodsSn(String goodsSn) {
        this.goodsSn = goodsSn;
        return this;
    }
    public String getGoodsName() {
        return goodsName;
    }

    public GoodsEntity setGoodsName(String goodsName) {
        this.goodsName = goodsName;
        return this;
    }
    public Integer getCategoryId() {
        return categoryId;
    }

    public GoodsEntity setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
        return this;
    }
    public Integer getShopId() {
        return shopId;
    }

    public GoodsEntity setShopId(Integer shopId) {
        this.shopId = shopId;
        return this;
    }
    public String getGoodsGallery() {
        return goodsGallery;
    }

    public GoodsEntity setGoodsGallery(String goodsGallery) {
        this.goodsGallery = goodsGallery;
        return this;
    }
    public String getGoodsKeywords() {
        return goodsKeywords;
    }

    public GoodsEntity setGoodsKeywords(String goodsKeywords) {
        this.goodsKeywords = goodsKeywords;
        return this;
    }
    public BigDecimal getGoodsPrice() {
        return goodsPrice;
    }

    public GoodsEntity setGoodsPrice(BigDecimal goodsPrice) {
        this.goodsPrice = goodsPrice;
        return this;
    }
    public String getGoodsBrief() {
        return goodsBrief;
    }

    public GoodsEntity setGoodsBrief(String goodsBrief) {
        this.goodsBrief = goodsBrief;
        return this;
    }
    public Boolean getOnSale() {
        return isOnSale;
    }

    public GoodsEntity setOnSale(Boolean isOnSale) {
        this.isOnSale = isOnSale;
        return this;
    }
    public Integer getSortOrder() {
        return sortOrder;
    }

    public GoodsEntity setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
        return this;
    }
    public String getPicUrl() {
        return picUrl;
    }

    public GoodsEntity setPicUrl(String picUrl) {
        this.picUrl = picUrl;
        return this;
    }
    public String getGoodsUnit() {
        return goodsUnit;
    }

    public GoodsEntity setGoodsUnit(String goodsUnit) {
        this.goodsUnit = goodsUnit;
        return this;
    }
    public String getGoodsDetail() {
        return goodsDetail;
    }

    public GoodsEntity setGoodsDetail(String goodsDetail) {
        this.goodsDetail = goodsDetail;
        return this;
    }
    public Integer getSpokesmanId() {
        return spokesmanId;
    }

    public GoodsEntity setSpokesmanId(Integer spokesmanId) {
        this.spokesmanId = spokesmanId;
        return this;
    }
    public Date getAddTime() {
        return addTime;
    }

    public GoodsEntity setAddTime(Date addTime) {
        this.addTime = addTime;
        return this;
    }
    public Date getUpdateTime() {
        return updateTime;
    }

    public GoodsEntity setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    @Override
    public String toString() {
        return "GoodsEntity{" +
            "goodsId=" + goodsId +
            ", goodsSn=" + goodsSn +
            ", goodsName=" + goodsName +
            ", categoryId=" + categoryId +
            ", shopId=" + shopId +
            ", goodsGallery=" + goodsGallery +
            ", goodsKeywords=" + goodsKeywords +
            ", goodsPrice=" + goodsPrice +
            ", goodsBrief=" + goodsBrief +
            ", isOnSale=" + isOnSale +
            ", sortOrder=" + sortOrder +
            ", picUrl=" + picUrl +
            ", goodsUnit=" + goodsUnit +
            ", goodsDetail=" + goodsDetail +
            ", spokesmanId=" + spokesmanId +
            ", addTime=" + addTime +
            ", updateTime=" + updateTime +
        "}";
    }
}
