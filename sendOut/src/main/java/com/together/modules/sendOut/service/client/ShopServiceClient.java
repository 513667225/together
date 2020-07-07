package com.together.modules.sendOut.service.client;

import com.together.util.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Agu
 */

@FeignClient("SHOP-SERVER")
@RequestMapping("/shopUser")
public interface ShopServiceClient {

    @RequestMapping("/shopShare")
    R shopShare(int shopuser_id,double balance);

}
