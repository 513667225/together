package com.together.modules.groupRoll.controller;


import com.together.annotation.Pmap;
import com.together.modules.groupRoll.entity.GroupRollEntity;
import com.together.modules.groupRoll.service.IGroupRollService;
import com.together.util.P;
import com.together.util.R;
import com.together.util.utli.ValidateUtli;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 
 * @since 2020-07-06
 */
@RestController
@RequestMapping("/groupRoll")
public class GroupRollController {

    @Autowired
    IGroupRollService groupRollService;

    //店铺发行拼团卷
    @RequestMapping("/groupRollInsert")
    public R GroupRollInsert(@Pmap P p) throws Exception {
        ValidateUtli.validateParams(p,"shopId","money","shopPublishMoney","shopAddress","inventory");
        GroupRollEntity groupRollEntity = p.thisToEntity(GroupRollEntity.class);
        Date date=new Date();  //添加时间
        String remark="";     //备注
        if(p.getString("remark")==null){
            remark=p.getString("remark");
        }
        groupRollEntity.setAddTime(date);
        groupRollEntity.setStatus(0);
        groupRollEntity.setRemark(remark);
        groupRollService.save(groupRollEntity);
        return R.success();
    }

    //根据店铺ID查询发行的所有优惠卷
    @RequestMapping("/selectGroupRollByShopId")
    public R selectGroupRollByShopId(@Pmap P p) throws Exception {
        ValidateUtli.validateParams(p,"shopId","rowIndex","limit");
        return groupRollService.selectGroupRollByShopId(p);
    }





}
