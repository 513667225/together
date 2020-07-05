package com.together.modules.reMoney.serviceClient;

import com.together.util.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Agu
 */
@FeignClient("USER_SERVER")
@RequestMapping("/user")
public interface UserServiceClient {

    @RequestMapping("/selectUserReferrer")
    R selectUserReferrer(@RequestParam("user_id") int user_id);

    @RequestMapping("/selectSeniorByUser")
    R selectSeniorByUser(@RequestParam("user_id") int user_id);


}
