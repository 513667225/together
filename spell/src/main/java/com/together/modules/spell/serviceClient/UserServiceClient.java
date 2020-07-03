package com.together.modules.spell.serviceClient;


import com.together.util.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("SERVER-USER")
@RequestMapping("/user")
public interface UserServiceClient {


    @RequestMapping(value = "/getUserById",method = RequestMethod.GET)
    R getUserById(@RequestParam("userId") int userId);

}
