package com.together.modules.user.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.together.modules.user.entity.UserEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

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
}
