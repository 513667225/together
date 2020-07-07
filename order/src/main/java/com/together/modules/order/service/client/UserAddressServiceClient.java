package com.together.modules.order.service.client;


import com.together.annotation.Pmap;
import com.together.util.P;
import com.together.util.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("SERVER-USER")
@RequestMapping("/userAddress")
public interface UserAddressServiceClient {

    @RequestMapping("/selectByuserAddress")
    public R selectByuserAddress(P p);

}
