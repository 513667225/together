package com.together.modules.order.serviceClient;


import com.together.util.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("SERVER-GOODS")
@RequestMapping("/goods")
public interface GoodsServiceClient {


    @RequestMapping("/queryGoodsById")
    R queryGoodsById(int goodsId);


}
