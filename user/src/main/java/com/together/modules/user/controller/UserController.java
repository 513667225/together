package com.together.modules.user.controller;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.together.modules.user.entity.UserEntity;
import com.together.modules.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.sql.Wrapper;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 
 * @since 2020-06-26
 */
@RestController
@RequestMapping("/user/user-entity")
public class UserController {

    @Autowired
    private IUserService iUserService;

    @GetMapping("/getUserPage")
    public R getUserPage(@RequestBody Map<String,Object> map){
        Page page = new Page((int)map.get("page"),(int)map.get("limit"));
        UserEntity userEntity = new UserEntity();
//        Map2JavaBeanUtil.transMap2Bean(map,userEntity);
        return R.ok(iUserService.page(page, Wrappers.query(userEntity)));
    }

    @PostMapping
    public  R saveOrUpdate(@RequestBody UserEntity userEntity){
        return R.ok(iUserService.saveOrUpdate(userEntity,Wrappers.update(userEntity)));
    }

    @DeleteMapping
    public  R removeById(@PathVariable String userId){
        return R.ok(iUserService.removeById(userId));
    }

}
