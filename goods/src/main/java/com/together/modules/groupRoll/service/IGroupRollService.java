package com.together.modules.groupRoll.service;

import com.together.modules.groupRoll.entity.GroupRollEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.together.util.P;
import com.together.util.R;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 
 * @since 2020-07-06
 */
public interface IGroupRollService extends IService<GroupRollEntity> {

    R selectGroupRollByShopId(P p);

    void groupRollInsert(P p) throws Exception;
}
