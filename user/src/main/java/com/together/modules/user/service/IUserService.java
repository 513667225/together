package com.together.modules.user.service;

import com.together.modules.user.entity.UserEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.together.util.P;

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


}
