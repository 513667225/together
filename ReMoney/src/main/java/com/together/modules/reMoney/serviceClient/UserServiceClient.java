package com.together.modules.reMoney.serviceClient;

import com.together.util.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Agu
 */
@FeignClient("SERVER-USER")
@RequestMapping("/user")
public interface UserServiceClient {

    @RequestMapping("/selectUserReferrerTo")
    R selectUserReferrer(@RequestParam("user_id") int userId);

    @RequestMapping("/selectSeniorByUser")
    R selectSeniorByUser(@RequestParam("user_id") int userId);

    @RequestMapping("/updateMoney")
    R updateMoney(@RequestParam("user_id") int userId,@RequestParam("balance")double balance);

    @RequestMapping("/updateMoney")
    R updateBalance(@RequestParam("user_id") int userId,@RequestParam("balance")double balance,@RequestParam("integral") double integral);


//    @RequestMapping


}
