package com.together.modules.admin.service.impl;

import com.together.modules.admin.entity.AdminEntity;
import com.together.modules.admin.mapper.AdminMapper;
import com.together.modules.admin.service.IAdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.together.util.P;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 
 * @since 2020-07-02
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, AdminEntity> implements IAdminService {

    @Override
    public List<AdminEntity> getProxyInfo(P p) {
        List<AdminEntity> adminId = baseMapper.getProxyInfo(p.getInt("adminId"));
        for (int i = 0; i < adminId.size(); i++) {
                
        }
        return null;
    }
}
