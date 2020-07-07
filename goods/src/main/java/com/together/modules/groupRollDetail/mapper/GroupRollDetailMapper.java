package com.together.modules.groupRollDetail.mapper;

import com.together.modules.groupRollDetail.entity.GroupRollDetailEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.together.modules.groupRollDetail.entity.RollDetailDo;
import com.together.util.P;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 
 * @since 2020-07-07
 */
public interface GroupRollDetailMapper extends BaseMapper<GroupRollDetailEntity> {

    List<RollDetailDo> selectRollDetail(P p);

    int selectRollDetailCount(P p);
}
