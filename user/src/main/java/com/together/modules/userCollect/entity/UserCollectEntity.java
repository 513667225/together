package com.together.modules.userCollect.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 收藏表
 * </p>
 *
 * @author 
 * @since 2020-06-28
 */
@TableName("user_collect")
public class UserCollectEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "collect_id", type = IdType.AUTO)
    private Integer collectId;

    /**
     * 用户表的用户ID
     */
    private Integer userId;

    /**
     * 收藏的商品ID
     */
    private Integer goodsId;

    /**
     * 创建时间
     */
    private Date addTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    public Integer getCollectId() {
        return collectId;
    }

    public UserCollectEntity setCollectId(Integer collectId) {
        this.collectId = collectId;
        return this;
    }
    public Integer getUserId() {
        return userId;
    }

    public UserCollectEntity setUserId(Integer userId) {
        this.userId = userId;
        return this;
    }
    public Integer getGoodsId() {
        return goodsId;
    }

    public UserCollectEntity setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
        return this;
    }
    public Date getAddTime() {
        return addTime;
    }

    public UserCollectEntity setAddTime(Date addTime) {
        this.addTime = addTime;
        return this;
    }
    public Date getUpdateTime() {
        return updateTime;
    }

    public UserCollectEntity setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    @Override
    public String toString() {
        return "UserCollectEntity{" +
            "collectId=" + collectId +
            ", userId=" + userId +
            ", goodsId=" + goodsId +
            ", addTime=" + addTime +
            ", updateTime=" + updateTime +
        "}";
    }
}
