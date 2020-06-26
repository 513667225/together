package com.together.modules.vip.entity;

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
@TableName("vip")
public class VipEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * VIP ID
     */
    private String vipId;

    /**
     * VIP级别
     */
    private Integer vipNum;

    public String getVipId() {
        return vipId;
    }

    public VipEntity setVipId(String vipId) {
        this.vipId = vipId;
        return this;
    }
    public Integer getVipNum() {
        return vipNum;
    }

    public VipEntity setVipNum(Integer vipNum) {
        this.vipNum = vipNum;
        return this;
    }

    @Override
    public String toString() {
        return "VipEntity{" +
            "vipId=" + vipId +
            ", vipNum=" + vipNum +
        "}";
    }
}
