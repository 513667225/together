package com.together.modules.userComment.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 评论表
 * </p>
 *
 * @author 
 * @since 2020-06-28
 */
@TableName("user_comment")
public class UserCommentEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "comment_id", type = IdType.AUTO)
    private Integer commentId;

    /**
     * 评论内容
     */
    private String commentContent;

    /**
     * 用户表的用户ID
     */
    private Integer userId;

    /**
     * 是否含有图片
     */
    private Boolean hasPicture;

    /**
     * 图片地址列表，采用JSON数组格式
     */
    private String picUrls;

    /**
     * 评分， 1-5
     */
    private Integer commentStar;

    /**
     * 创建时间
     */
    private Date addTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    public Integer getCommentId() {
        return commentId;
    }

    public UserCommentEntity setCommentId(Integer commentId) {
        this.commentId = commentId;
        return this;
    }
    public String getCommentContent() {
        return commentContent;
    }

    public UserCommentEntity setCommentContent(String commentContent) {
        this.commentContent = commentContent;
        return this;
    }
    public Integer getUserId() {
        return userId;
    }

    public UserCommentEntity setUserId(Integer userId) {
        this.userId = userId;
        return this;
    }
    public Boolean getHasPicture() {
        return hasPicture;
    }

    public UserCommentEntity setHasPicture(Boolean hasPicture) {
        this.hasPicture = hasPicture;
        return this;
    }
    public String getPicUrls() {
        return picUrls;
    }

    public UserCommentEntity setPicUrls(String picUrls) {
        this.picUrls = picUrls;
        return this;
    }
    public Integer getCommentStar() {
        return commentStar;
    }

    public UserCommentEntity setCommentStar(Integer commentStar) {
        this.commentStar = commentStar;
        return this;
    }
    public Date getAddTime() {
        return addTime;
    }

    public UserCommentEntity setAddTime(Date addTime) {
        this.addTime = addTime;
        return this;
    }
    public Date getUpdateTime() {
        return updateTime;
    }

    public UserCommentEntity setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    @Override
    public String toString() {
        return "UserCommentEntity{" +
            "commentId=" + commentId +
            ", commentContent=" + commentContent +
            ", userId=" + userId +
            ", hasPicture=" + hasPicture +
            ", picUrls=" + picUrls +
            ", commentStar=" + commentStar +
            ", addTime=" + addTime +
            ", updateTime=" + updateTime +
        "}";
    }
}
