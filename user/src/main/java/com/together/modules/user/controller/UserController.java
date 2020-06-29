package com.together.modules.user.controller;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.together.modules.user.entity.UserEntity;
import com.together.modules.user.service.IUserService;
import com.together.util.Map2JavaBeanUtil;
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

    @GetMapping("/getGoodsPage")
    public R getGoodsPage(@RequestParam Map<String,Object> map) throws Exception {
        UserEntity userEntity = new UserEntity();
        int pageNum = 1;
        int limit = 10;
        if (map.get("page")!=null) {
            pageNum = (int) map.get("page");
        }
        if (map.get("limit")!=null) {
            limit = (int) map.get("limit");
        }
        if(map.get("user")!=null){
            userEntity = (UserEntity) map.get("user");
        }
        Page page = new Page(pageNum,limit);
        Map2JavaBeanUtil.transMap2Bean(map,userEntity);
        return R.ok(userService.page(page, Wrappers.query(userEntity)));
    }

    @GetMapping("/getGoodsList")
    public R getGoodsList(){
        return R.ok(userService.list());
    }

    /**
     * 根据商品id查询信息
     * @param userId
     * @return
     */
    @GetMapping("/{userId}")
    public R getProductId(@PathVariable("userId") String userId){
        return R.ok(userService.getById(userId));
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
