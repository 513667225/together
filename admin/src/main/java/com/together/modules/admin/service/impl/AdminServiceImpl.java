package com.together.modules.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.together.modules.admin.entity.AdminEntity;
import com.together.modules.admin.mapper.AdminMapper;
import com.together.modules.admin.service.IAdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.together.util.MapUtil;
import com.together.util.P;
import com.together.util.R;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    public List getProxyInfo(P p) throws Exception {
        List<Map<String,Object>> returnList = new ArrayList();
        List<Integer> adminIdList = baseMapper.getProxyInfo(p.getInt("adminId"));
        for (Integer integer : adminIdList) {
            List<Map<String,Object>> orderList = baseMapper.getOrderList(integer);
            for (Map<String, Object> stringObjectMap : orderList) {
                MapUtil.mapKeySetLine2Upper(stringObjectMap);
            }
            returnList.addAll(orderList);
        }
        return returnList;
    }

    @Override
    public R update(P p) {
        UpdateWrapper<AdminEntity> adminEntityUpdateWrapper = new UpdateWrapper<AdminEntity>().setSql("balance=balance+"+p.getBigDecimal("balance")).eq("admin_id",p.getInt("admin_id"));
        return R.success("success", update(adminEntityUpdateWrapper));
    }
}
