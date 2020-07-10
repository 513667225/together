package com.together.modules.sendOut.service.impl;

import com.together.modules.sendOut.service.DeclineService;
import com.together.modules.sendOut.service.client.UserServiceClient;
import com.together.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author Agu
 */
@Service
public class DeclineServiceImpl implements DeclineService {


    @Autowired
    UserServiceClient userServiceClient;


    @Override
    public R decline(int user_id, BigDecimal price) {
        //TODO 要处理扣减代售次数
        return userServiceClient.updateMoney(user_id,price);
    }
}
