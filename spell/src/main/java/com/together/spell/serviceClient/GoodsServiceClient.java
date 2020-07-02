package com.together.modules.spell.serviceClient;


import com.together.util.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("SERVER-GOODS")
@RequestMapping("/goods")
public interface GoodsServiceClient {


    @RequestMapping("/queryGoodsById")
    R queryGoodsById(@RequestParam("goodsId") int goodsId);


}
