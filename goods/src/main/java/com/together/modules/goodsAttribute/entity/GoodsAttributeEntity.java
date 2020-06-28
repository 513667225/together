package com.together.modules.goodsAttribute.entity;

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
@TableName("goods_attribute")
public class GoodsAttributeEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer attributeId;

    /**
     * 商品ID
     */
    private Integer goodsId;

    /**
     * 商品参数名称
     */
    private String attributeName;

    /**
     * 商品参数值
     */
    private String attributeValue;

    /**
     * 创建时间
     */
    private Date addTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    public Integer getAttributeId() {
        return attributeId;
    }

    public GoodsAttributeEntity setAttributeId(Integer attributeId) {
        this.attributeId = attributeId;
        return this;
    }
    public Integer getGoodsId() {
        return goodsId;
    }

    public GoodsAttributeEntity setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
        return this;
    }
    public String getAttributeName() {
        return attributeName;
    }

    public GoodsAttributeEntity setAttributeName(String attributeName) {
        this.attributeName = attributeName;
        return this;
    }
    public String getAttributeValue() {
        return attributeValue;
    }

    public GoodsAttributeEntity setAttributeValue(String attributeValue) {
        this.attributeValue = attributeValue;
        return this;
    }
    public Date getAddTime() {
        return addTime;
    }

    public GoodsAttributeEntity setAddTime(Date addTime) {
        this.addTime = addTime;
        return this;
    }
    public Date getUpdateTime() {
        return updateTime;
    }

    public GoodsAttributeEntity setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    @Override
    public String toString() {
        return "GoodsAttributeEntity{" +
            "attributeId=" + attributeId +
            ", goodsId=" + goodsId +
            ", attributeName=" + attributeName +
            ", attributeValue=" + attributeValue +
            ", addTime=" + addTime +
            ", updateTime=" + updateTime +
        "}";
    }
}
