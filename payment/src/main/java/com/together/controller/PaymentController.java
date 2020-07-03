package com.together.controller;

import com.alibaba.fastjson.JSONObject;
import com.together.annotation.Pmap;
import com.together.template.PaymentTemplate;
import com.together.util.P;
import com.together.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller("/payment")
public class PaymentController {

    @Autowired
    PaymentTemplate paymentTemplate;

    /**
     * 支付
     * @param p
     * @return
     */
    @PostMapping("/zhifu")
    public R zhifu(@Pmap P p){
        JSONObject jsonObject = paymentTemplate.payment(p);
        R r=R.success("success",jsonObject);
        r.put("code",200);
        return r;
    }

}
