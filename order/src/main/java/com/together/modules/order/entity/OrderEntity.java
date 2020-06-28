package com.together.modules.order.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 订单表
 * </p>
 *
 * @author 
 * @since 2020-06-28
 */
@TableName("order")
public class OrderEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "order_id", type = IdType.AUTO)
    private Integer orderId;

    /**
     * 用户表的用户ID
     */
    private Integer userId;

    /**
     * 订单编号
     */
    private String orderSn;

    /**
     * 订单状态
     */
    private Integer orderStatus;

    /**
     * 售后状态，0是可申请，1是用户已申请，2是管理员审核通过，3是管理员退款成功，4是管理员审核拒绝，5是用户已取消
     */
    private Integer aftersaleStatus;

    /**
     * 收货人名称
     */
    private String orderConsignee;

    /**
     * 收货人手机号
     */
    private String orderTel;

    /**
     * 收货具体地址
     */
    private String orderAddress;

    /**
     * 用户订单留言
     */
    private String orderMessage;

    /**
     * 商品总费用
     */
    private BigDecimal goodsPrice;

    /**
     * 配送费用
     */
    private BigDecimal freightPrice;

    /**
     * 拼豆减免费用
     */
    private BigDecimal pindouPrice;

    /**
     * 订单费用， = goods_price + freight_price - coupon_price
     */
    private BigDecimal orderPrice;

    /**
     * 实付费用， = order_price - integral_price
     */
    private BigDecimal actualPrice;

    /**
     * 微信付款编号
     */
    private String payId;

    /**
     * 微信付款时间
     */
    private Date payTime;

    /**
     * 发货编号
     */
    private String shipSn;

    /**
     * 发货快递公司
     */
    private String shipChannel;

    /**
     * 发货开始时间
     */
    private Date shipTime;

    /**
     * 用户确认收货时间
     */
    private Date confirmTime;

    /**
     * 待评价订单商品数量
     */
    private Integer comments;

    /**
     * 订单关闭时间
     */
    private Date endTime;

    /**
     * 创建时间
     */
    private Date addTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    public Integer getOrderId() {
        return orderId;
    }

    public OrderEntity setOrderId(Integer orderId) {
        this.orderId = orderId;
        return this;
    }
    public Integer getUserId() {
        return userId;
    }

    public OrderEntity setUserId(Integer userId) {
        this.userId = userId;
        return this;
    }
    public String getOrderSn() {
        return orderSn;
    }

    public OrderEntity setOrderSn(String orderSn) {
        this.orderSn = orderSn;
        return this;
    }
    public Integer getOrderStatus() {
        return orderStatus;
    }

    public OrderEntity setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
        return this;
    }
    public Integer getAftersaleStatus() {
        return aftersaleStatus;
    }

    public OrderEntity setAftersaleStatus(Integer aftersaleStatus) {
        this.aftersaleStatus = aftersaleStatus;
        return this;
    }
    public String getOrderConsignee() {
        return orderConsignee;
    }

    public OrderEntity setOrderConsignee(String orderConsignee) {
        this.orderConsignee = orderConsignee;
        return this;
    }
    public String getOrderTel() {
        return orderTel;
    }

    public OrderEntity setOrderTel(String orderTel) {
        this.orderTel = orderTel;
        return this;
    }
    public String getOrderAddress() {
        return orderAddress;
    }

    public OrderEntity setOrderAddress(String orderAddress) {
        this.orderAddress = orderAddress;
        return this;
    }
    public String getOrderMessage() {
        return orderMessage;
    }

    public OrderEntity setOrderMessage(String orderMessage) {
        this.orderMessage = orderMessage;
        return this;
    }
    public BigDecimal getGoodsPrice() {
        return goodsPrice;
    }

    public OrderEntity setGoodsPrice(BigDecimal goodsPrice) {
        this.goodsPrice = goodsPrice;
        return this;
    }
    public BigDecimal getFreightPrice() {
        return freightPrice;
    }

    public OrderEntity setFreightPrice(BigDecimal freightPrice) {
        this.freightPrice = freightPrice;
        return this;
    }
    public BigDecimal getPindouPrice() {
        return pindouPrice;
    }

    public OrderEntity setPindouPrice(BigDecimal pindouPrice) {
        this.pindouPrice = pindouPrice;
        return this;
    }
    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public OrderEntity setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
        return this;
    }
    public BigDecimal getActualPrice() {
        return actualPrice;
    }

    public OrderEntity setActualPrice(BigDecimal actualPrice) {
        this.actualPrice = actualPrice;
        return this;
    }
    public String getPayId() {
        return payId;
    }

    public OrderEntity setPayId(String payId) {
        this.payId = payId;
        return this;
    }
    public Date getPayTime() {
        return payTime;
    }

    public OrderEntity setPayTime(Date payTime) {
        this.payTime = payTime;
        return this;
    }
    public String getShipSn() {
        return shipSn;
    }

    public OrderEntity setShipSn(String shipSn) {
        this.shipSn = shipSn;
        return this;
    }
    public String getShipChannel() {
        return shipChannel;
    }

    public OrderEntity setShipChannel(String shipChannel) {
        this.shipChannel = shipChannel;
        return this;
    }
    public Date getShipTime() {
        return shipTime;
    }

    public OrderEntity setShipTime(Date shipTime) {
        this.shipTime = shipTime;
        return this;
    }
    public Date getConfirmTime() {
        return confirmTime;
    }

    public OrderEntity setConfirmTime(Date confirmTime) {
        this.confirmTime = confirmTime;
        return this;
    }
    public Integer getComments() {
        return comments;
    }

    public OrderEntity setComments(Integer comments) {
        this.comments = comments;
        return this;
    }
    public Date getEndTime() {
        return endTime;
    }

    public OrderEntity setEndTime(Date endTime) {
        this.endTime = endTime;
        return this;
    }
    public Date getAddTime() {
        return addTime;
    }

    public OrderEntity setAddTime(Date addTime) {
        this.addTime = addTime;
        return this;
    }
    public Date getUpdateTime() {
        return updateTime;
    }

    public OrderEntity setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    @Override
    public String toString() {
        return "OrderEntity{" +
            "orderId=" + orderId +
            ", userId=" + userId +
            ", orderSn=" + orderSn +
            ", orderStatus=" + orderStatus +
            ", aftersaleStatus=" + aftersaleStatus +
            ", orderConsignee=" + orderConsignee +
            ", orderTel=" + orderTel +
            ", orderAddress=" + orderAddress +
            ", orderMessage=" + orderMessage +
            ", goodsPrice=" + goodsPrice +
            ", freightPrice=" + freightPrice +
            ", pindouPrice=" + pindouPrice +
            ", orderPrice=" + orderPrice +
            ", actualPrice=" + actualPrice +
            ", payId=" + payId +
            ", payTime=" + payTime +
            ", shipSn=" + shipSn +
            ", shipChannel=" + shipChannel +
            ", shipTime=" + shipTime +
            ", confirmTime=" + confirmTime +
            ", comments=" + comments +
            ", endTime=" + endTime +
            ", addTime=" + addTime +
            ", updateTime=" + updateTime +
        "}";
    }
}
