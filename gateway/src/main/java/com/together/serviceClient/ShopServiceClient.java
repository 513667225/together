package com.together.serviceClient;

import com.together.util.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("SERVER-SHOP")
@RequestMapping("/shop")
public interface ShopServiceClient {

    @RequestMapping("/getShopPage")
    R getShopPage(int userId);


}
