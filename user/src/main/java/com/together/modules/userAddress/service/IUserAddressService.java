package com.together.modules.userAddress.service;

import com.together.modules.userAddress.entity.UserAddressEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.together.util.P;

import java.util.List;

/**
 * <p>
 * 收货地址表 服务类
 * </p>
 *
 * @author 
 * @since 2020-06-28
 */
public interface IUserAddressService extends IService<UserAddressEntity> {

    List<UserAddressEntity> selectAllUserAddress(P p);
}
