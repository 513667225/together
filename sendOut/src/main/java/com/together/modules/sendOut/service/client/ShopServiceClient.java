package com.together.modules.sendOut.service.client;

import com.together.util.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Agu
 */

@FeignClient("SERVER-SHOP")
@RequestMapping("/shopUser")
public interface ShopServiceClient {

    @RequestMapping("/shopShare")
    R shopShare(@RequestParam("shopuser_id") int shopuser_id, @RequestParam("balance")double balance);

}
