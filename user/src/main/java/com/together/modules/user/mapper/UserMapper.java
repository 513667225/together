package com.together.modules.user.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.together.modules.user.entity.UserEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.together.modules.user.entity.UserReferrerDo;
import com.together.entity.UserSuperstratumRelationDo;

import java.util.List;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author 
 * @since 2020-06-28
 */
public interface UserMapper extends BaseMapper<UserEntity> {

    UserEntity selectOne(QueryWrapper<Object> eq);

    List<UserReferrerDo> selectByuserReferrerId(Integer referrerId);

    UserSuperstratumRelationDo selectUserSuperstratum(Integer user_id);
}
