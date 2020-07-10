package com.together.modules.shop.service.client;


import com.together.util.P;
import com.together.util.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("SERVER-ADMIN")
@RequestMapping("/admin")
public interface AdminServiceClient {

    @RequestMapping("/getAdminByRegId")
    public R getAdminByRegId(@RequestBody P p);

}
