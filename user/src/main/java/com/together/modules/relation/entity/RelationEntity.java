package com.together.modules.relation.entity;

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
@TableName("relation")
public class RelationEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * VIP关系ID
     */
    private String relationId;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 上级ID
     */
    private String relationPid;

    public String getRelationId() {
        return relationId;
    }

    public RelationEntity setRelationId(String relationId) {
        this.relationId = relationId;
        return this;
    }
    public String getUserId() {
        return userId;
    }

    public RelationEntity setUserId(String userId) {
        this.userId = userId;
        return this;
    }
    public String getRelationPid() {
        return relationPid;
    }

    public RelationEntity setRelationPid(String relationPid) {
        this.relationPid = relationPid;
        return this;
    }

    @Override
    public String toString() {
        return "RelationEntity{" +
            "relationId=" + relationId +
            ", userId=" + userId +
            ", relationPid=" + relationPid +
        "}";
    }
}
