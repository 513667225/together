package com.together.modules.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.together.modules.user.entity.UserEntity;
import com.together.modules.user.mapper.UserMapper;
import com.together.modules.user.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.together.util.P;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author 
 * @since 2020-06-28
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements IUserService {

    @Override
    public UserEntity getUserByName(P p) {
        String userName = (String) p.get("userName");
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("userName",userName);
        return baseMapper.selectOne(wrapper);
    }
}
