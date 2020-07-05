package com.together.modules.reMoney.service.imp;

import com.together.enun.GoodsLevel;
import com.together.enun.UserRelationshipCase;
import com.together.modules.reMoney.service.ReMoneyService;
import com.together.modules.reMoney.serviceClient.UserServiceClient;
import com.together.util.P;
import com.together.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Agu
 */
@Service
public class ReMoneyServiceImpl  implements ReMoneyService {

    @Autowired
    private UserServiceClient userServiceClient;

    @Override
    public void reMoney(int user_id, GoodsLevel goodsLevel) {
        //计算推荐津贴：直推：5% 间推：5%
        R r = userServiceClient.selectUserReferrer(user_id);
        P p  = new P((Map) r.get("data")) ;
        Integer  directReferrerId = p.getInt("directReferrerId");
        Integer  managerReferrerId = p.getInt("managerReferrerId");
        //TODO 给直推人和间推人分发推荐津贴
        if (directReferrerId != null) {
            //TODO 调用用户修改钱接口 直推人分红： goodsLevel.getDirectPush()

        }
        if (managerReferrerId != null) {
            //TODO 调用用户修改钱接口  间推人分成: goodsLevel.getIndirectPush()
        }
        //计算团队津贴 : 向上查询  {false},{true}  总监{}
        // 第一个经理5% 第二个经理2% 只查2级   经理层最大波比5%+2%=7%
        //  第一个总监收益
        //  case1: 中途有经理+经理  --> 2%
        //  case2: 中途有经理  --> 4%
        //  case3: 中途无经理  --> 9%
        //  第二个总监: 2%
        R SeniorByUserReust = userServiceClient.selectSeniorByUser(user_id);
        (List) SeniorByUserReust.get("data")
    }

    public List  getUserForNeedAllowance(List list, List returnList, UserRelationshipCase userRelationshipCase){
            if (returnList ==null){
                returnList = new ArrayList();
            }
        for (Object o : list) {
            switch (userRelationshipCase){
                case CASE1:
                    //1经理
                    //o.get  eq 经理? case2  : case5
                        break;
                case CASE2:
                    //2经理
                    //o.get  eq 经理? case3 : case 4
                        break;
                case CASE3:
                    //2经理1总监
                    //o.get eq 总监?  case6 : return;
                        break;
                case CASE4://
                    //1经理1总监   case6 : return
                        break;
                case CASE5:
                    //无经理1总监   o.get eq 总监？ case6 : return
                    break;
                case CASE6:
                    //总监后总监
                    break;
            }


        }
    return null;

    }


}
