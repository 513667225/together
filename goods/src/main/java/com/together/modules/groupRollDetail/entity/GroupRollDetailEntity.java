package com.together.modules.groupRollDetail.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author 
 * @since 2020-07-07
 */
@TableName("group_roll_detail")
public class GroupRollDetailEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer gdetailId;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 拼团卷id
     */
    private Integer gid;

    /**
     * 拼团卷状态，1拼团中，2未使用，3，已使用，4，未拼中，5取消
     */
    private Integer status;

    /**
     * 抢到时间
     */
    private Date addTime;

    /**
     * 过期时间
     */
    private Date expirationTime;

    /**
     * 使用时间
     */
    private Date useTime;

    public Date getUseTime() {
        return useTime;
    }

    public void setUseTime(Date useTime) {
        this.useTime = useTime;
    }

    public Integer getGdetailId() {
        return gdetailId;
    }

    public GroupRollDetailEntity setGdetailId(Integer gdetailId) {
        this.gdetailId = gdetailId;
        return this;
    }
    public Integer getUserId() {
        return userId;
    }

    public GroupRollDetailEntity setUserId(Integer userId) {
        this.userId = userId;
        return this;
    }
    public Integer getGid() {
        return gid;
    }

    public GroupRollDetailEntity setGid(Integer gid) {
        this.gid = gid;
        return this;
    }
    public Integer getStatus() {
        return status;
    }

    public GroupRollDetailEntity setStatus(Integer status) {
        this.status = status;
        return this;
    }
    public Date getAddTime() {
        return addTime;
    }

    public GroupRollDetailEntity setAddTime(Date addTime) {
        this.addTime = addTime;
        return this;
    }
    public Date getExpirationTime() {
        return expirationTime;
    }

    public GroupRollDetailEntity setExpirationTime(Date expirationTime) {
        this.expirationTime = expirationTime;
        return this;
    }

    @Override
    public String toString() {
        return "GroupRollDetailEntity{" +
            "gdetailId=" + gdetailId +
            ", userId=" + userId +
            ", gid=" + gid +
            ", status=" + status +
            ", addTime=" + addTime +
            ", expirationTime=" + expirationTime +
        "}";
    }
}
