package com.together.modules.user.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.together.annotation.Pmap;
import com.together.modules.user.entity.UserEntity;
import com.together.modules.user.service.IUserService;
import com.together.modules.user.utli.ResponseUtli;
import com.together.modules.user.utli.ValidateUtli;
import com.together.util.Map2JavaBeanUtil;
import com.together.util.P;
import org.apache.catalina.connector.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
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
     * @param p
     * @return
     */
    @PostMapping("/login")
    public R save(@Pmap P p){
        try {
            ValidateUtli.validateParams(p,"code","userName","avatarUrl");
            UserEntity userEntity = userService.getUserLogin(p);
            return R.ok(ResponseUtli.StringToMap("userId",userEntity.getUserId())).setCode(200);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return R.failed("登录失败").setCode(500).setData(ResponseUtli.NullToMap());
    }




    /**
     * 支付
     * @param p
     * @return
     */
    @PostMapping("/zhifu")
    public R zhifu(@Pmap P p){
        JSONObject jsonObject = userService.payGroupOrder(p);
        return R.ok(jsonObject).setCode(200);
    }

    /**
     * 单个查询订单状态
     * id 团购单id
     * @return R
     * @throws Exception
     */
    @RequestMapping("/getGroupUserState")
    @ResponseBody
    public R getGroupUserState(@Pmap P p) throws Exception {
        ValidateUtli.validateParams(p,"id");
        Map<String, Object> groupUserState = userService.getGroupUserState(p);
        return R.ok(groupUserState).setCode(200);
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
