package com.together.modules.userAddress.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 收货地址表
 * </p>
 *
 * @author 
 * @since 2020-06-28
 */
@TableName("user_address")
public class UserAddressEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "address_id", type = IdType.AUTO)
    private Integer addressId;

    /**
     * 收货人名称
     */
    private String userName;

    /**
     * 用户表的用户ID
     */
    private Integer userId;

    /**
     * 行政区域表的省ID
     */
    private String province;

    /**
     * 行政区域表的市ID
     */
    private String city;

    /**
     * 行政区域表的区县ID
     */
    private String county;

    /**
     * 详细收货地址
     */
    private String addressDetail;

    /**
     * 地区编码
     */
    private String areaCode;

    /**
     * 邮政编码
     */
    private String postalCode;

    /**
     * 手机号码
     */
    private String tel;

    /**
     * 是否默认地址
     */
    private Boolean isDefault;

    /**
     * 创建时间
     */
    private Date addTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    public Integer getAddressId() {
        return addressId;
    }

    public UserAddressEntity setAddressId(Integer addressId) {
        this.addressId = addressId;
        return this;
    }
    public String getUserName() {
        return userName;
    }

    public UserAddressEntity setUserName(String userName) {
        this.userName = userName;
        return this;
    }
    public Integer getUserId() {
        return userId;
    }

    public UserAddressEntity setUserId(Integer userId) {
        this.userId = userId;
        return this;
    }
    public String getProvince() {
        return province;
    }

    public UserAddressEntity setProvince(String province) {
        this.province = province;
        return this;
    }
    public String getCity() {
        return city;
    }

    public UserAddressEntity setCity(String city) {
        this.city = city;
        return this;
    }
    public String getCounty() {
        return county;
    }

    public UserAddressEntity setCounty(String county) {
        this.county = county;
        return this;
    }
    public String getAddressDetail() {
        return addressDetail;
    }

    public UserAddressEntity setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
        return this;
    }
    public String getAreaCode() {
        return areaCode;
    }

    public UserAddressEntity setAreaCode(String areaCode) {
        this.areaCode = areaCode;
        return this;
    }
    public String getPostalCode() {
        return postalCode;
    }

    public UserAddressEntity setPostalCode(String postalCode) {
        this.postalCode = postalCode;
        return this;
    }
    public String getTel() {
        return tel;
    }

    public UserAddressEntity setTel(String tel) {
        this.tel = tel;
        return this;
    }
    public Boolean getIsDefault() {
        return isDefault;
    }

    public UserAddressEntity setIsDefault(Boolean isDefault) {
        this.isDefault = isDefault;
        return this;
    }
    public Date getAddTime() {
        return addTime;
    }

    public UserAddressEntity setAddTime(Date addTime) {
        this.addTime = addTime;
        return this;
    }
    public Date getUpdateTime() {
        return updateTime;
    }

    public UserAddressEntity setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    @Override
    public String toString() {
        return "UserAddressEntity{" +
            "addressId=" + addressId +
            ", userName=" + userName +
            ", userId=" + userId +
            ", province=" + province +
            ", city=" + city +
            ", county=" + county +
            ", addressDetail=" + addressDetail +
            ", areaCode=" + areaCode +
            ", postalCode=" + postalCode +
            ", tel=" + tel +
            ", isDefault=" + isDefault +
            ", addTime=" + addTime +
            ", updateTime=" + updateTime +
        "}";
    }
}
