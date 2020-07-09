package com.together.modules.admin.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.together.annotation.Login;
import com.together.annotation.Pmap;
import com.together.modules.admin.entity.AdminEntity;
import com.together.modules.admin.service.IAdminService;
import com.together.modules.region.entity.RegionEntity;
import com.together.modules.region.service.IRegionService;
import com.together.parameter.RedisParamenter;
import com.together.util.P;
import com.together.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Response;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

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
@Login
public class AdminController {
    @Autowired
    private IAdminService adminService;
    @Autowired
    private IRegionService regionService;
    @Autowired
    ValueOperations operations;

    @GetMapping("/getAdminPage")
    public R getAdminPage(@Pmap P p) throws Exception {
        Integer page = p.getInt("page");
        Integer limit = p.getInt("limit");
        p.initPageArg();
        Page<AdminEntity> objectPage = new Page<>(page,limit);
        p.remove("page");
        p.remove("limit");
        p.remove("rowIndex");
        p.removeByKey(p);
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


    /**
     * 商家登录方法
     */
    @GetMapping("/adminLogin")
    public R adminLogin (@Pmap P p) throws Exception {
        String adminPassword = p.getString("adminPassword");
        AdminEntity one = adminService.getOne(new QueryWrapper<AdminEntity>().eq("admin_name",p.getString("adminName")));
        if(null!=one){
            if(one.getAdminPassword().equals(adminPassword)){
                //生成uuid
                String id = UUID.randomUUID().toString().replaceAll("-", "");
                //将对象放入redis
                operations.set(id,one);
                Cookie cookie = new Cookie(RedisParamenter.ADMIN_LOING_USER_REDIS_KEY,id);
                HttpServletResponse response = p.getResponse();
                response.addCookie(cookie);
                return R.success("0",cookie);
            }else{
                return R.success("1");
            }
        }
        return R.success("-1");
    }

    /**
     * 根据region_id获取Admin
     * @return
     */
    @GetMapping("getAdminByRegId")
    public R getAdminByRegId(@Pmap @RequestBody P p) throws Exception {
        AdminEntity entity = adminService.getOne(new QueryWrapper<AdminEntity>().eq("region_id", p.getInt("city")));
        return R.success("success",entity);
    }
}
