package com.together.modules.admin.service;

import com.together.modules.admin.entity.AdminEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.together.util.P;
import com.together.util.R;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 
 * @since 2020-07-02
 */
public interface IAdminService extends IService<AdminEntity> {

    List getProxyInfo(P p) throws Exception;

}
