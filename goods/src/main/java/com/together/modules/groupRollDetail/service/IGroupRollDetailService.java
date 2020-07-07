package com.together.modules.groupRollDetail.service;

import com.together.modules.groupRollDetail.entity.GroupRollDetailEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.together.modules.groupRollDetail.entity.RollDetailDo;
import com.together.util.P;
import com.together.util.R;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 
 * @since 2020-07-07
 */
public interface IGroupRollDetailService extends IService<GroupRollDetailEntity> {

    R selectRollDetail(P p);
}
