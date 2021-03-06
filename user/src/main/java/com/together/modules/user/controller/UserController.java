package com.together.modules.user.controller;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.together.annotation.PassToken;
import com.together.annotation.Pmap;
import com.together.entity.Audience;
import com.together.modules.user.entity.UserEntity;
import com.together.entity.UserSuperstratumRelationDo;
import com.together.modules.user.entity.UserEntityDo;
import com.together.modules.user.service.IUserService;
import com.together.modules.user.utli.UserEntityToUserEntityDoUtli;
import com.together.util.P;
import com.together.util.R;
import com.together.util.utli.JwtUtil;
import com.together.util.utli.ResponseUtli;
import com.together.util.utli.ValidateUtli;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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

    @Autowired
    Audience audience;


    /**
     * 根据用户id查询
     * @param userId
     * @return
     */
    @RequestMapping(value = "/getUserById",method = RequestMethod.GET)
    R getUserById(@RequestParam("userId") int userId){
        UserEntity userEntity = userService.getById(userId);
        return R.success().data(UserEntityToUserEntityDoUtli.userEntityToUserEntityDoUtli(userEntity));
    }


    /**
     * 分页查询user
     * @param p
     * @return
     * @throws Exception
     */
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


    /**
     * 查询所有用户
     * @return
     */
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
    @PassToken
    @PostMapping("/login")
    public R save(@Pmap P p){
        try {
            ValidateUtli.validateParams(p,"code","userName","avatarUrl");
            UserEntity userEntity = userService.getUserLogin(p);
            UserEntityDo userEntityDo= UserEntityToUserEntityDoUtli.userEntityToUserEntityDoUtli(userEntity);
            String token = JwtUtil.createJWT(UUID.randomUUID().toString(), JSON.toJSONString(userEntityDo));
            return R.success("success", ResponseUtli.StringToMap("user",userEntityDo,"token",token));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return R.error("登录失败").data(ResponseUtli.NullToMap());
    }



    @PassToken
    @RequestMapping("/test")
    public R test(@Pmap P p) throws Exception{
        userService.test(p);
        return R.success();
    }

    /**
     * 用户添加修改手机号
     * @param p
     * @return
     * @throws Exception
     */
    @PostMapping("/insertAndUpdatePhone")
    public R updateUserPhone(@Pmap P p) throws Exception {
        return userService.updateUserPhone(p);
    }


    //根据用户id查询推荐人和推荐人的推荐人
    @RequestMapping("/selectUserReferrerTo")
    public R selectUserReferrerTo(@Pmap P p) throws Exception {
        ValidateUtli.validateParams(p,"user_id");
        Map<String, Object> stringObjectMap = userService.selectUserReferrerTo(p);
        return R.success("success").data(stringObjectMap);
    }

    //根据用户id查询所有邀请人
    @PassToken
    @RequestMapping("/selectUserReferrer")
    public R selectUserALlInviter(@Pmap P p) throws Exception {
        ValidateUtli.validateParams(p,"userId");
        List<UserEntity> userEntityList=userService.selectUserALlInviter(p);
        return R.success("success").data(userEntityList);
    }


    //根据用户id  查询上层所有经理或者总监级别以上
    @RequestMapping("/selectSeniorByUser")
    @PassToken
    public R selectUserReferrerInManager(@Pmap P p) throws Exception {
        ArrayList<UserSuperstratumRelationDo>  userSuperstratumRelationDos=userService.userReferrerDorecursion(p);
        return R.success("success").data(userSuperstratumRelationDos);
    }


    /**
     * 修改
     * @param p
     * @return
     */
    @PassToken
    @RequestMapping("/updateById")
    public R updateById(@Pmap P p) throws Exception {
        UserEntity userEntity = p.thisToEntity(UserEntity.class);
        return R.success("success",userService.updateById(userEntity));
    }

    //修改余额，拼豆，购物金，积分
    @RequestMapping("/updateMoney")
    @PassToken
    public R updateMoney(@Pmap P p) throws Exception {
        ValidateUtli.validateParams(p,"user_id");
        userService.updateMoney(p);
        return R.success("success");
    }


    /**
     * 生成用户小程序邀请码图片
     * @param p
     * @throws Exception
     */
    @RequestMapping("/createCodeImag")
    public void createCodeImag(@Pmap P p) throws Exception {
        ValidateUtli.validateParams(p,"path","user_id");
        userService.createCodeImag(p.getString("path"),p.getString("user_id"),p.getResponse());
    }

}
