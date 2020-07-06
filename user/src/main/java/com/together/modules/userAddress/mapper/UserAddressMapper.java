package com.together.modules.userAddress.mapper;

import com.together.modules.userAddress.entity.UserAddressEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 收货地址表 Mapper 接口
 * </p>
 *
 * @author 
 * @since 2020-06-28
 */
public interface UserAddressMapper extends BaseMapper<UserAddressEntity> {

    List<UserAddressEntity> selectAllUserAddress(Integer userId);
}
