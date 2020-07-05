package com.together.modules.user.entity;

import java.util.List;

public class UserReferrerDo {

    private Integer user_id;
    private Integer user_level;
    private Integer user_referrer;
    private String routing;
    private List<UserReferrerDo> userReferrerDos;

    @Override
    public String toString() {
        return "UserReferrerDo{" +
                "user_id=" + user_id +
                ", user_level=" + user_level +
                ", user_referrer=" + user_referrer +
                ", routing='" + routing + '\'' +
                '}';
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getUser_level() {
        return user_level;
    }

    public void setUser_level(Integer user_level) {
        this.user_level = user_level;
    }

    public Integer getUser_referrer() {
        return user_referrer;
    }

    public void setUser_referrer(Integer user_referrer) {
        this.user_referrer = user_referrer;
    }

    public String getRouting() {
        return routing;
    }

    public void setRouting(String routing) {
        this.routing = routing;
    }

    public List<UserReferrerDo> getUserReferrerDos() {
        return userReferrerDos;
    }

    public void setUserReferrerDos(List<UserReferrerDo> userReferrerDos) {
        this.userReferrerDos = userReferrerDos;
    }
}
