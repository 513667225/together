package com.together.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author 
 * @since 2020-07-02
 */
@TableName("admin")
public class AdminEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer adminId;

    /**
     * 管理员名称
     */
    private String adminNikename;

    /**
     * 管理员登录账号
     */
    private String adminName;

    /**
     * 管理员密码
     */
    private String adminPassword;

    /**
     * 管理员级别,0 超级管理员,1省代理,2 市代理
     */
    private String adminLevel;

    /**
     * 创建时间
     */
    private Date addTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 余额
     */
@TableField(update="%s+#{balance}")
    private BigDecimal balance;

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public AdminEntity setAdminId(Integer adminId) {
        this.adminId = adminId;
        return this;
    }
    public String getAdminNikename() {
        return adminNikename;
    }

    public AdminEntity setAdminNikename(String adminNikename) {
        this.adminNikename = adminNikename;
        return this;
    }
    public String getAdminName() {
        return adminName;
    }

    public AdminEntity setAdminName(String adminName) {
        this.adminName = adminName;
        return this;
    }
    public String getAdminPassword() {
        return adminPassword;
    }

    public AdminEntity setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
        return this;
    }
    public String getAdminLevel() {
        return adminLevel;
    }

    public AdminEntity setAdminLevel(String adminLevel) {
        this.adminLevel = adminLevel;
        return this;
    }
    public Date getAddTime() {
        return addTime;
    }

    public AdminEntity setAddTime(Date addTime) {
        this.addTime = addTime;
        return this;
    }
    public Date getUpdateTime() {
        return updateTime;
    }

    public AdminEntity setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    @Override
    public String toString() {
        return "AdminEntity{" +
            "adminId=" + adminId +
            ", adminNikename=" + adminNikename +
            ", adminName=" + adminName +
            ", adminPassword=" + adminPassword +
            ", adminLevel=" + adminLevel +
            ", addTime=" + addTime +
            ", updateTime=" + updateTime +
        "}";
    }
}
