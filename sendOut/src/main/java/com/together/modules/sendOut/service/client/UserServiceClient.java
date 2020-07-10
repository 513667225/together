package com.together.modules.sendOut.service.client;

import com.together.util.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

/**
 * @author Agu
 */
@FeignClient("SERVER-USER")
@RequestMapping("/user")
public interface UserServiceClient {

    @RequestMapping("/updateMoney")
    R updateMoney(@RequestParam("user_id") int userId,@RequestParam("integral") BigDecimal integral);


//    @RequestMapping


}
