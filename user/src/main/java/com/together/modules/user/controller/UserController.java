package com.together.modules.user.controller;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.together.annotation.Pmap;
import com.together.modules.user.entity.UserEntity;
import com.together.modules.user.service.IUserService;
import com.together.util.Map2JavaBeanUtil;
import com.together.util.P;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author 
 * @since 2020-06-28
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("/getUserPage")
    public R getGoodsPage(@Pmap P p) throws Exception {
        Integer page = p.getInt("page");
        Integer limit = p.getInt("limit");
        Page page1  = new Page(page, limit);
        page1 = userService.page(page1, Wrappers.query(p.thisToEntity(UserEntity.class)));
        return R.ok(page1);
    }

    @GetMapping("/getUserList")
    public R getUserList(){
        return R.ok(userService.list());
    }

    /**
     * 根据用户账号查询信息
     * @param p
     * @return
     */
    @GetMapping("/getUserByName")
    public R getUserByName(@Pmap P p){
        return R.ok(userService.getUserByName(p));
    }

    /**
     * 新增
     * @param userEntity
     * @return
     */
    @PostMapping
    public R save(@RequestBody UserEntity userEntity){
        return R.ok(userService.save(userEntity));
    }

    /**
     * 修改
     * @param userEntity
     * @return
     */
    @PutMapping
    public R updateById(@RequestBody UserEntity userEntity){
        return R.ok(userService.updateById(userEntity));
    }

}
