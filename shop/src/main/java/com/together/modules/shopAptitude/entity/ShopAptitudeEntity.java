package com.together.modules.shopAptitude.entity;

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
@TableName("shop_aptitude")
public class ShopAptitudeEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer aptitudeId;

    /**
     * 商家ID
     */
    private Integer shopId;

    /**
     * 单位名称
     */
    private String aptitudeCompany;

    /**
     * 经营地址
     */
    private String aptitudeAddress;

    /**
     * 法人代表
     */
    private String aptitudeLegalPerson;

    /**
     * 许可证号
     */
    private String aptitudeKey;

    /**
     * 从业资质图片
     */
    private String aptitudePic;

    /**
     * 创建时间
     */
    private Date addTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    public Integer getAptitudeId() {
        return aptitudeId;
    }

    public ShopAptitudeEntity setAptitudeId(Integer aptitudeId) {
        this.aptitudeId = aptitudeId;
        return this;
    }
    public Integer getShopId() {
        return shopId;
    }

    public ShopAptitudeEntity setShopId(Integer shopId) {
        this.shopId = shopId;
        return this;
    }
    public String getAptitudeCompany() {
        return aptitudeCompany;
    }

    public ShopAptitudeEntity setAptitudeCompany(String aptitudeCompany) {
        this.aptitudeCompany = aptitudeCompany;
        return this;
    }
    public String getAptitudeAddress() {
        return aptitudeAddress;
    }

    public ShopAptitudeEntity setAptitudeAddress(String aptitudeAddress) {
        this.aptitudeAddress = aptitudeAddress;
        return this;
    }
    public String getAptitudeLegalPerson() {
        return aptitudeLegalPerson;
    }

    public ShopAptitudeEntity setAptitudeLegalPerson(String aptitudeLegalPerson) {
        this.aptitudeLegalPerson = aptitudeLegalPerson;
        return this;
    }
    public String getAptitudeKey() {
        return aptitudeKey;
    }

    public ShopAptitudeEntity setAptitudeKey(String aptitudeKey) {
        this.aptitudeKey = aptitudeKey;
        return this;
    }
    public String getAptitudePic() {
        return aptitudePic;
    }

    public ShopAptitudeEntity setAptitudePic(String aptitudePic) {
        this.aptitudePic = aptitudePic;
        return this;
    }
    public Date getAddTime() {
        return addTime;
    }

    public ShopAptitudeEntity setAddTime(Date addTime) {
        this.addTime = addTime;
        return this;
    }
    public Date getUpdateTime() {
        return updateTime;
    }

    public ShopAptitudeEntity setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    @Override
    public String toString() {
        return "ShopAptitudeEntity{" +
            "aptitudeId=" + aptitudeId +
            ", shopId=" + shopId +
            ", aptitudeCompany=" + aptitudeCompany +
            ", aptitudeAddress=" + aptitudeAddress +
            ", aptitudeLegalPerson=" + aptitudeLegalPerson +
            ", aptitudeKey=" + aptitudeKey +
            ", aptitudePic=" + aptitudePic +
            ", addTime=" + addTime +
            ", updateTime=" + updateTime +
        "}";
    }
}
