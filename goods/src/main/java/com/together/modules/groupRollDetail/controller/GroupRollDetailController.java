package com.together.modules.groupRollDetail.controller;


import com.together.annotation.Pmap;
import com.together.modules.groupRollDetail.entity.GroupRollDetailEntity;
import com.together.modules.groupRollDetail.entity.RollDetailDo;
import com.together.modules.groupRollDetail.service.IGroupRollDetailService;
import com.together.modules.groupRollDetail.service.impl.GroupRollDetailServiceImpl;
import com.together.util.P;
import com.together.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 
 * @since 2020-07-07
 */
@RestController
@RequestMapping("/groupRollDetail")
public class GroupRollDetailController {

    @Autowired
    IGroupRollDetailService groupRollDetailService;

    /**
     * 用户抢到卷   优惠卷订单
     * @param p
     * @return
     * @throws Exception
     */
    @RequestMapping("/insertRollDetail")
    public R insertRollDetail(@Pmap P p) throws Exception {
        GroupRollDetailEntity groupRollDetailEntity = p.thisToEntity(GroupRollDetailEntity.class);
        groupRollDetailService.save(groupRollDetailEntity);
        return R.success();
    }


    /**
     * 根据拼团卷id  查询此拼团卷明细
     */
    @RequestMapping("/selectRollDetail")
    public R selectRollDetail(@Pmap P p){
        p.batchToInt("rowIndex","limit");
        return groupRollDetailService.selectRollDetail(p);
    }


}
