package com.together.modules.user.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.together.annotation.Pmap;
import com.together.modules.user.entity.UserEntity;
import com.together.modules.user.service.IUserService;
import com.together.modules.user.utli.ResponseUtli;
import com.together.modules.user.utli.ValidateUtli;
import com.together.util.Map2JavaBeanUtil;
import com.together.util.P;
import com.together.util.R;
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



    @RequestMapping(value = "/getUserById",method = RequestMethod.GET)
    R getUserById(@RequestParam("userId") int userId){
        UserEntity userEntity = userService.getById(userId);
        return R.success().data(userEntity);
    }

    @GetMapping("/getUserPage")
    public R getGoodsPage(@Pmap P p) throws Exception {
        Integer page = p.getInt("page");
        Integer limit = p.getInt("limit");
        p.initPageArg();
        p.remove("page");
        p.remove("limit");
        p.remove("rowIndex");
        Page<UserEntity> objectPage = new Page<>(1, 10);
        Page<UserEntity> pageObject=userService.page(objectPage,new QueryWrapper<UserEntity>().allEq(p));
        return R.success("success",pageObject.getRecords()).set("count",pageObject.getTotal());
    }

    @GetMapping("/getUserList")
    public R getUserList(){
        return R.success("success",userService.list());
    }

    /**
     * 根据用户账号查询信息
     * @param p
     * @return
     */
    @GetMapping("/getUserByName")
    public R getUserByName(@Pmap P p){
        return R.success("success",userService.getUserByName(p));
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
            R r=R.success("success",ResponseUtli.StringToMap("userId",userEntity.getUserId()));
            r.put("code",200);
            return r;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return R.error("登录失败").data(ResponseUtli.NullToMap());
    }




    /**
     * 支付
     * @param p
     * @return
     */
    @PostMapping("/zhifu")
    public R zhifu(@Pmap P p){
        JSONObject jsonObject = userService.payGroupOrder(p);
        R r=R.success("success",jsonObject);
        r.put("code",200);
        return r;
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
        R r=R.success("success",groupUserState);
        r.put("code",200);
        return r;
    }




    /**
     * 修改
     * @param userEntity
     * @return
     */
    @PutMapping
    public R updateById(@RequestBody UserEntity userEntity){
        return R.success("success",userService.updateById(userEntity));
    }

}
