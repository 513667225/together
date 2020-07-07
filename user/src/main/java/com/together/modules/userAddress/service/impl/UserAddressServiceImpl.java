package com.together.modules.userAddress.service.impl;

import com.together.modules.userAddress.entity.UserAddressEntity;
import com.together.modules.userAddress.mapper.UserAddressMapper;
import com.together.modules.userAddress.service.IUserAddressService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.together.util.P;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 收货地址表 服务实现类
 * </p>
 *
 * @author 
 * @since 2020-06-28
 */
@Service
public class UserAddressServiceImpl extends ServiceImpl<UserAddressMapper, UserAddressEntity> implements IUserAddressService {

    @Override
    public List<UserAddressEntity> selectAllUserAddress(P p) {
        return baseMapper.selectAllUserAddress(p.getInt("userId"));
    }
}
