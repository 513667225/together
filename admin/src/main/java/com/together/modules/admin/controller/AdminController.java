package com.together.modules.admin.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.together.annotation.Pmap;
import com.together.modules.admin.entity.AdminEntity;
import com.together.modules.admin.service.IAdminService;
import com.together.util.P;
import com.together.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 
 * @since 2020-07-02
 */
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private IAdminService adminService;

    @GetMapping("/getAdminPage")
    public R getAdminPage(@Pmap P p) throws Exception {
        Integer page = p.getInt("page");
        Integer limit = p.getInt("limit");
        p.initPageArg();
        Page<AdminEntity> objectPage = new Page<>(page,limit);
        p.remove("page");
        p.remove("limit");
        p.remove("rowIndex");
        if(""==p.getString("admin_name")){
            p.remove("admin_name");
        }
        if(""==p.getString("admin_nikename")){
            p.remove("admin_nikename");
        }
        Page<AdminEntity> pageObject = adminService.page(objectPage,new QueryWrapper<AdminEntity>().allEq(p));
        return R.success("success",pageObject.getRecords()).set("count",pageObject.getTotal());
    }



    @GetMapping("/getProxyInfo")
    public R getProxyInfo(@Pmap P p) throws Exception {
        return R.success("success",adminService.getProxyInfo(p));
    }


    @RequestMapping("/updateAdmin")
    public R updateAdmin(@Pmap P p){
        return adminService.update(p);
    }

}
