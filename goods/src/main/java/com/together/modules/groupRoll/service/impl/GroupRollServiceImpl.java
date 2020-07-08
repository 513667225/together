package com.together.modules.groupRoll.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.together.modules.groupRoll.entity.GroupRollEntity;
import com.together.modules.groupRoll.mapper.GroupRollMapper;
import com.together.modules.groupRoll.service.IGroupRollService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.together.util.P;
import com.together.util.R;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 
 * @since 2020-07-06
 */
@Service
public class GroupRollServiceImpl extends ServiceImpl<GroupRollMapper, GroupRollEntity> implements IGroupRollService {

    @Override
    public R selectGroupRollByShopId(P p) {
        Integer shopId = p.getInt("shopId");
        Integer limit = p.getInt("limit");
        QueryWrapper<GroupRollEntity> queryWrapper = new QueryWrapper<GroupRollEntity>().eq("shop_id", shopId);
        Page<GroupRollEntity> objectPage = new Page<>(p.getInt("page"),limit);
        Page<GroupRollEntity> groupRollEntityPage = baseMapper.selectPage(objectPage, queryWrapper);
        List<GroupRollEntity> records = groupRollEntityPage.getRecords();

        //总共发行了多少团队卷，有多少被抢了，有多少被抢没使用，有多少已使用

        //根据金额分类，有多少被抢了，有多少被抢没使用，有多少已使用

        //根据Issue_number分组按时间排序，
        return R.success().data(records).set("total",groupRollEntityPage.getTotal());
    }
}
