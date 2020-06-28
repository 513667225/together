package com.together.modules.userAddress.service.impl;

import com.together.modules.userAddress.entity.UserAddressEntity;
import com.together.modules.userAddress.mapper.UserAddressMapper;
import com.together.modules.userAddress.service.IUserAddressService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
