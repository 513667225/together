package com.together.serviceClient;//package com.together.serviceClient;

import com.together.util.P;
import com.together.util.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("SERVER-PAYMENT")
@RequestMapping("/user")
public interface UserServiceClient {


    @RequestMapping("/getUserById")
     R getUserById(@RequestParam("userId") String userId);
}
