package com.together.modules.region.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.together.annotation.Pmap;
import com.together.modules.admin.util.R;
import com.together.modules.region.entity.RegionEntity;
import com.together.modules.region.service.IRegionService;
import com.together.util.P;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 行政区域表 前端控制器
 * </p>
 *
 * @author 
 * @since 2020-07-06
 */
@RestController
@RequestMapping("/region")
public class RegionController {
    @Autowired
    IRegionService regionService;


    /**
     * 根据codeid查询region对象
     * @return
     */
    @GetMapping("/getRegionByCode")
    public R getRegionByCode(@Pmap P p) throws Exception {
        return R.success("success",regionService.getRegionByCode(p.getString("code")));
    }

}
