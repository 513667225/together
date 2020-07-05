package com.together.modules.user.service;

import com.together.modules.user.entity.UserEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.together.entity.UserSuperstratumRelationDo;
import com.together.util.P;

import java.util.ArrayList;
import java.util.Map;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author 
 * @since 2020-06-28
 */
public interface IUserService extends IService<UserEntity> {


    /**
     * 查询单个用户
     * @param p
     * @return
     */
    UserEntity getUserByName(P p);
    UserEntity getUserLogin(Map<String,Object> param);
    Map<String, Object> getGroupUserState(P p);
    Map<String, Object> selectUserReferrer(P p) throws Exception;

    void test(P p);

    Map<String, Object> selectUserReferrerInManager(P p);

    void updateUserPhone(P p) throws Exception;

    ArrayList<UserSuperstratumRelationDo> userReferrerDorecursion(P p) throws Exception;
}
