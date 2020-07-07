package com.together.modules.sendOut.service.client;

import com.together.util.P;
import com.together.util.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Agu
 */
@FeignClient("SERVER-ORDER")
@RequestMapping("/order")
public interface OrderServiceClient {

    @RequestMapping("/newOrder")
    R newOrder(@RequestBody P p);

}
