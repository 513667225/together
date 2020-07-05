package com.together.modules.user.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.together.annotation.Pmap;
import com.together.modules.user.entity.UserEntity;
import com.together.entity.UserSuperstratumRelationDo;
import com.together.modules.user.service.IUserService;
import com.together.util.P;
import com.together.util.R;
import com.together.util.utli.ResponseUtli;
import com.together.util.utli.ValidateUtli;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

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
    public R getUserPage(@Pmap P p) throws Exception {
        Integer page = p.getInt("page");
        Integer limit = p.getInt("limit");
        p.initPageArg();
        p.remove("page");
        p.remove("limit");
        p.remove("rowIndex");
        if(""==p.getString("user_mobile")){
            p.remove("user_mobile");
        }
        if(""==p.getString("user_name")){
            p.remove("user_name");
        }
        Page<UserEntity> objectPage = new Page<>(page, limit);
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
            R r=R.success("success", ResponseUtli.StringToMap("userId",userEntity.getUserId()));
            r.put("code",200);
            return r;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return R.error("登录失败").data(ResponseUtli.NullToMap());
    }



    @RequestMapping("/test")
    public R test(@Pmap P p){
        userService.test(p);
        return R.success();
    }
    @RequestMapping("/insertAndUpdatePhone")
    public R updateUserPhone(@Pmap P p) throws Exception {
        userService.updateUserPhone(p);
        return R.error("登录失败").data(ResponseUtli.NullToMap());
    }


    //根据用户id查询推荐人和推荐人的推荐人
//    @RequestMapping("/selectUserReferrer")
//    public R selectUserReferrer(@Pmap P p) throws Exception {
//        Map<String, Object> stringObjectMap = userService.selectUserReferrer(p);
//        return R.success("success").data(stringObjectMap);
//    }

    //根据用户id查询上级服务经理
    @RequestMapping("/UserReferrerDorecursion")
    public R selectUserReferrerInManager(@Pmap P p) throws Exception {
        ArrayList<UserSuperstratumRelationDo>  userSuperstratumRelationDos=userService.userReferrerDorecursion(p);
        return R.success("success").data(userSuperstratumRelationDos);
    }


    public R getAndInsertUserPhone(@Pmap P p){
      return R.success();
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
