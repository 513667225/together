package com.together.modules.userAddress.controller;


import com.together.annotation.Pmap;
import com.together.enun.TipMsgEnum;
import com.together.modules.userAddress.entity.UserAddressEntity;
import com.together.modules.userAddress.service.IUserAddressService;
import com.together.util.P;
import com.together.util.R;
import com.together.util.utli.ValidateUtli;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 收货地址表 前端控制器
 * </p>
 *
 * @author 
 * @since 2020-06-28
 */
@RestController
@RequestMapping("/userAddress")
public class UserAddressController {

    @Autowired
    IUserAddressService userAddressService;

    /**
     * 根据用户id查询所有用户地址
     * @param p
     * @return
     * @throws Exception
     */
    @RequestMapping("/selectAllUserAddress")
    public R selectAllUserAddress(@Pmap P p) throws Exception {
        ValidateUtli.validateParams(p,"userId");
        List<UserAddressEntity> userAddressEntities= userAddressService.selectAllUserAddress(p);
        return R.success().data(userAddressEntities);
    }


    /**
     * 用户地址新增
     * @param userAddressEntity
     * @return
     * @throws Exception
     */
    @RequestMapping("/insertUserAddress")
    public R insertUserAddress(@RequestBody UserAddressEntity userAddressEntity) throws Exception {
        try {
            userAddressService.save(userAddressEntity);
            return R.success();
        }catch (Exception e){
            throw new Exception(TipMsgEnum.INSERT_USER_ADDRESS.getMsg());
        }
    }

    /**
     * 用户地址删除
     * @param p
     * @return
     * @throws Exception
     */
    @RequestMapping("/deleteUserAddress")
    public R deleteUserAddress(@Pmap P p) throws Exception {
        ValidateUtli.validateParams(p,"addressId");
        try {
            userAddressService.removeById(p.getInt("addressId"));
            return R.success();
        }catch (Exception e){
            throw new Exception(TipMsgEnum.DELETE_USER_ADDRESS.getMsg());
        }
    }

    /**
     * 用户地址修改
     * @param userAddressEntity
     * @return
     * @throws Exception
     */
    @RequestMapping("/updateUserAddress")
    public R updateUserAddress(@RequestBody UserAddressEntity userAddressEntity) throws Exception {
        try {
            userAddressService.updateById(userAddressEntity);
            return R.success();
        }catch (Exception e){
            throw new Exception(TipMsgEnum.UPDATE_USER_ADDRESS.getMsg());
        }
    }

}
