package com.together.controller;


import com.together.annotation.Pmap;
import com.together.entity.UserInfo;
import com.together.serviceClient.ShopServiceClient;
import com.together.serviceClient.UserServiceClient;
import com.together.util.Map2JavaBeanUtil;
import com.together.util.P;
import com.together.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class LoginController {


    @Autowired
   private UserServiceClient userServiceClient;

    @Autowired
    private ShopServiceClient shopServiceClient;


    @Autowired
    private ValueOperations valueOperations;

    /**
     *  登陆
     * @param p
     * @return
     * @throws Exception
     */
//    @RequestMapping("/login")
//    public R login(@Pmap P p) throws Exception{
//        String password  = p.getString("password");
//        p.remove("password");
//        R result = userServiceClient.getUserByName(p);
//        if (result==null){
//            return R.error("用户不存在");
//        }
//        String password1 = String.valueOf(result.get("password"));
//        if (!password.equals(password1)) {
//            return R.error("密码不正确");
//        }
//
//        UserInfo userInfo = Map2JavaBeanUtil.transMap2Bean(result, UserInfo.class);
//
//        R shopPage = shopServiceClient.getShopPage(userInfo.getUserId());
//        userInfo.setShopId(Integer.parseInt(String.valueOf(shopPage.get("shop_id"))));
//        String uuid = UUID.randomUUID().toString();
//        valueOperations.set(uuid,userInfo);
//        HttpServletResponse response = p.getResponse();
//        Cookie cookie = new Cookie(UserInfo.USER_COOKIE_KEY, uuid);
//        response.addCookie(cookie);
//        return R.success();
//    }

}
