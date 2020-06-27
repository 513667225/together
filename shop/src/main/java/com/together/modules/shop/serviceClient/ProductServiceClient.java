package com.together.modules.shop.serviceClient;


import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FeignClient("SERVER-PRODUCT")
@RequestMapping("/product")
public interface ProductServiceClient {

//    public R getProductPage(Page page, ProductEntity productEntity)


    @RequestMapping(value = "/getProductPage" )
    public R getProductPage(@RequestBody Map<String,Object> map);

}
