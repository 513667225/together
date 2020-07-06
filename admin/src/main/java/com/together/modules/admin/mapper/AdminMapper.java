package com.together.modules.admin.mapper;

import com.together.modules.admin.entity.AdminEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.together.util.P;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 
 * @since 2020-07-02
 */
public interface AdminMapper extends BaseMapper<AdminEntity> {

    List<AdminEntity> getProxyInfo(int adminId);

    List getOrderList(int adminId);

}
