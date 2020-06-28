package com.together.modules.orderGoods.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 订单商品表
 * </p>
 *
 * @author 
 * @since 2020-06-28
 */
@TableName("order_goods")
public class OrderGoodsEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 订单表的订单ID
     */
    private Integer orderId;

    /**
     * 商品表的商品ID
     */
    private Integer goodsId;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 商品编号
     */
    private String goodsSn;

    /**
     * 商品货品表的货品ID
     */
    private Integer productId;

    /**
     * 商品货品的购买数量
     */
    private Integer number;

    /**
     * 商品货品的售价
     */
    private BigDecimal price;

    /**
     * 商品货品的规格列表
     */
    private String specifications;

    /**
     * 商品货品图片或者商品图片
     */
    private String picUrl;

    /**
     * 订单商品评论，如果是-1，则超期不能评价；如果是0，则可以评价；如果其他值，则是comment表里面的评论ID。
     */
    private Integer comment;

    /**
     * 创建时间
     */
    private Date addTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 逻辑删除
     */
    private Boolean deleted;

    public Integer getId() {
        return id;
    }

    public OrderGoodsEntity setId(Integer id) {
        this.id = id;
        return this;
    }
    public Integer getOrderId() {
        return orderId;
    }

    public OrderGoodsEntity setOrderId(Integer orderId) {
        this.orderId = orderId;
        return this;
    }
    public Integer getGoodsId() {
        return goodsId;
    }

    public OrderGoodsEntity setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
        return this;
    }
    public String getGoodsName() {
        return goodsName;
    }

    public OrderGoodsEntity setGoodsName(String goodsName) {
        this.goodsName = goodsName;
        return this;
    }
    public String getGoodsSn() {
        return goodsSn;
    }

    public OrderGoodsEntity setGoodsSn(String goodsSn) {
        this.goodsSn = goodsSn;
        return this;
    }
    public Integer getProductId() {
        return productId;
    }

    public OrderGoodsEntity setProductId(Integer productId) {
        this.productId = productId;
        return this;
    }
    public Integer getNumber() {
        return number;
    }

    public OrderGoodsEntity setNumber(Integer number) {
        this.number = number;
        return this;
    }
    public BigDecimal getPrice() {
        return price;
    }

    public OrderGoodsEntity setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }
    public String getSpecifications() {
        return specifications;
    }

    public OrderGoodsEntity setSpecifications(String specifications) {
        this.specifications = specifications;
        return this;
    }
    public String getPicUrl() {
        return picUrl;
    }

    public OrderGoodsEntity setPicUrl(String picUrl) {
        this.picUrl = picUrl;
        return this;
    }
    public Integer getComment() {
        return comment;
    }

    public OrderGoodsEntity setComment(Integer comment) {
        this.comment = comment;
        return this;
    }
    public Date getAddTime() {
        return addTime;
    }

    public OrderGoodsEntity setAddTime(Date addTime) {
        this.addTime = addTime;
        return this;
    }
    public Date getUpdateTime() {
        return updateTime;
    }

    public OrderGoodsEntity setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }
    public Boolean getDeleted() {
        return deleted;
    }

    public OrderGoodsEntity setDeleted(Boolean deleted) {
        this.deleted = deleted;
        return this;
    }

    @Override
    public String toString() {
        return "OrderGoodsEntity{" +
            "id=" + id +
            ", orderId=" + orderId +
            ", goodsId=" + goodsId +
            ", goodsName=" + goodsName +
            ", goodsSn=" + goodsSn +
            ", productId=" + productId +
            ", number=" + number +
            ", price=" + price +
            ", specifications=" + specifications +
            ", picUrl=" + picUrl +
            ", comment=" + comment +
            ", addTime=" + addTime +
            ", updateTime=" + updateTime +
            ", deleted=" + deleted +
        "}";
    }
}
