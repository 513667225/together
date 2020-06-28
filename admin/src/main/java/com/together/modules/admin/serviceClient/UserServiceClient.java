package com.together.modules.admin.serviceClient;


import com.baomidou.mybatisplus.extension.api.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@FeignClient("server-user")
@RequestMapping("/user/user-entity")
public interface UserServiceClient {

//    public R getProductPage(Page page, ProductEntity productEntity)


    @RequestMapping(value = "/getUserPage" )
    public R getUserPage(@RequestBody Map<String,Object> map);

}
