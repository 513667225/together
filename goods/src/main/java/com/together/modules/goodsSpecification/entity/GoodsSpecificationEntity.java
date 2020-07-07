package com.together.modules.goodsSpecification.entity;

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
@TableName("goods_specification")
public class GoodsSpecificationEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer specificationId;

    private Integer goodsId;

    /**
     * 商品规格名称
     */
    private String specificationName;

    /**
     * 商品规格图片
     */
    private String picUrl;

    /**
     * 创建时间
     */
    private Date addTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    public Integer getSpecificationId() {
        return specificationId;
    }

    public GoodsSpecificationEntity setSpecificationId(Integer specificationId) {
        this.specificationId = specificationId;
        return this;
    }
    public Integer getGoodsId() {
        return goodsId;
    }

    public GoodsSpecificationEntity setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
        return this;
    }
    public String getSpecificationName() {
        return specificationName;
    }

    public GoodsSpecificationEntity setSpecificationName(String specificationName) {
        this.specificationName = specificationName;
        return this;
    }
    public String getPicUrl() {
        return picUrl;
    }

    public GoodsSpecificationEntity setPicUrl(String picUrl) {
        this.picUrl = picUrl;
        return this;
    }
    public Date getAddTime() {
        return addTime;
    }

    public GoodsSpecificationEntity setAddTime(Date addTime) {
        this.addTime = addTime;
        return this;
    }
    public Date getUpdateTime() {
        return updateTime;
    }

    public GoodsSpecificationEntity setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    @Override
    public String toString() {
        return "GoodsSpecificationEntity{" +
            "specificationId=" + specificationId +
            ", goodsId=" + goodsId +
            ", specificationName=" + specificationName +
            ", picUrl=" + picUrl +
            ", addTime=" + addTime +
            ", updateTime=" + updateTime +
        "}";
    }
}
