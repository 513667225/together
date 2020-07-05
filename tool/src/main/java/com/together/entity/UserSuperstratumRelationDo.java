package com.together.entity;

public class UserSuperstratumRelationDo {
    private Integer user_id;
    private Integer topRefereeId;
    private Integer userLevel;
    private Integer userReferrer;

    @Override
    public String toString() {
        return "UserSuperstratumRelationDo{" +
                "user_id=" + user_id +
                ", topRefereeId=" + topRefereeId +
                ", userLevel=" + userLevel +
                ", userReferrer=" + userReferrer +
                '}';
    }

    public Integer getUserReferrer() {
        return userReferrer;
    }

    public void setUserReferrer(Integer userReferrer) {
        this.userReferrer = userReferrer;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getTopRefereeId() {
        return topRefereeId;
    }

    public void setTopRefereeId(Integer topRefereeId) {
        this.topRefereeId = topRefereeId;
    }

    public Integer getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(Integer userLevel) {
        this.userLevel = userLevel;
    }
}
