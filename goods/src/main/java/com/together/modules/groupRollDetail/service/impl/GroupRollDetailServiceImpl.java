package com.together.modules.groupRollDetail.service.impl;

import com.together.modules.groupRollDetail.entity.GroupRollDetailEntity;
import com.together.modules.groupRollDetail.entity.RollDetailDo;
import com.together.modules.groupRollDetail.mapper.GroupRollDetailMapper;
import com.together.modules.groupRollDetail.service.IGroupRollDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.together.util.P;
import com.together.util.R;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 
 * @since 2020-07-07
 */
@Service
public class GroupRollDetailServiceImpl extends ServiceImpl<GroupRollDetailMapper, GroupRollDetailEntity> implements IGroupRollDetailService {

    @Override
    public R selectRollDetail(P p) {
        int rollDetailCount = baseMapper.selectRollDetailCount(p);
        return R.success().data(baseMapper.selectRollDetail(p)).set("total",rollDetailCount);
    }
}
