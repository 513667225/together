package com.together.modules.reMoney.service.imp;

import com.together.entity.AllowanceCache;
import com.together.entity.CaseEntity;
import com.together.entity.UserSuperstratumRelationDo;
import com.together.enun.GoodsLevel;
import com.together.enun.UserRelationshipCase;
import com.together.modules.reMoney.service.ReMoneyService;
import com.together.modules.reMoney.serviceClient.UserServiceClient;
import com.together.util.P;
import com.together.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author Agu
 */
@Service
public class ReMoneyServiceImpl implements ReMoneyService {

    @Autowired
    private UserServiceClient userServiceClient;

    @Override
    public void reMoney(int user_id, GoodsLevel goodsLevel) {
        //计算推荐津贴：直推：5% 间推：5%
        R r = userServiceClient.selectUserReferrer(user_id);
        P p = new P((Map) r.get("data"));
        Integer directReferrerId = p.getInt("directReferrerId");
        Integer managerReferrerId = p.getInt("managerReferrerId");
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
        List<UserSuperstratumRelationDo> data = (List<UserSuperstratumRelationDo>) SeniorByUserReust.get("data");
        List userForNeedAllowance = getUserForNeedAllowance(data.iterator(), null, new CaseEntity(UserRelationshipCase.CASE1,true,true), goodsLevel);
        //TODO userForNeedAllowance存入缓存
    }


    /**
     * 给这个用户的上级发放团队津贴
     *
     * @param returnList           需要发放津贴的上级
     * @param goodsLevel           本次拼团的档位 {@link GoodsLevel}
     * @return
     */
    public  List getUserForNeedAllowance(Iterator<UserSuperstratumRelationDo> iterator, List<AllowanceCache> returnList, CaseEntity caseEntity, GoodsLevel goodsLevel) {
        if (returnList == null) {
            returnList = new LinkedList<AllowanceCache>();
        }

        while (iterator.hasNext() && caseEntity.isCaseController()) {
            UserSuperstratumRelationDo next = iterator.next();
            do{
                userRelationshipCase(next,returnList,caseEntity,goodsLevel);
            }while (caseEntity.isAgain());

        }
        return null;

    }

    /**
     * 计算某个人的所得 并返回流程
     *
     */
    public  CaseEntity userRelationshipCase(UserSuperstratumRelationDo next, List<AllowanceCache> returnList, CaseEntity caseEntity, GoodsLevel goodsLevel) {
        //当前上级ID
        Integer user_id = next.getUser_id();
        //当前算钱比率
        double rate = caseEntity.getUserRelationshipCase().getRate();
        double moneyNum = 0;
        boolean isNeedAdd = false;
        switch (caseEntity.getUserRelationshipCase()) {
            case CASE1:
                //1经理
                //o.get  eq 经理? case2  : case5
                //判断是否有经理
                if (next.getUserLevel() == 2) {
                    isNeedAdd = true;
                    moneyNum = caseEntity.getUserRelationshipCase().reMoneyNum(goodsLevel);
                    caseEntity.setUserRelationshipCase(UserRelationshipCase.CASE2);
                    caseEntity.setAgain(false);
                    break;
                }
                //若无任何经理，直接进入环节5判断是否有总监
                caseEntity.setUserRelationshipCase(UserRelationshipCase.CASE5);
                break;
            case CASE2:
                //2个经理
                //o.get  eq 经理? case3 : case 4
                if (next.getUserLevel() == 2) {
                    isNeedAdd = true;
                    moneyNum = caseEntity.getUserRelationshipCase().reMoneyNum(goodsLevel);
                    caseEntity.setUserRelationshipCase(UserRelationshipCase.CASE3);
                    caseEntity.setAgain(false);
                    break;
                }
                //若无第二个经理，则进入环节4 1经理1总监情况
                caseEntity.setUserRelationshipCase(UserRelationshipCase.CASE4);
                break;
            case CASE3:
                //2经理1总监
                //o.get eq 总监?  case6 : return;
                //若有总监 进入环节6判断是否有下一个总监
            case CASE4://
                //1经理1总监   case6 : return
            case CASE5:
                //无经理1总监   o.get eq 总监？ case6 : return
            case CASE6:
                //总监后总监
                //若无第二个总监 则环节结束
                if (next.getUserLevel() == 3) {
                    isNeedAdd = true;
                    moneyNum = caseEntity.getUserRelationshipCase().reMoneyNum(goodsLevel);
                    if (caseEntity.getUserRelationshipCase().equals(UserRelationshipCase.CASE6)) {
                        caseEntity.setAgain(false);
                        caseEntity.setCaseController(false);
                    }
                    caseEntity.setUserRelationshipCase(UserRelationshipCase.CASE6);
                    break;
                }
                caseEntity.setAgain(false);
//                caseEntity.setCaseController(false);
                break;
        }
        //需要算钱的时候加钱 否则直接环节推进
        if (isNeedAdd) {
            //发钱
            addMoney(user_id, moneyNum);
            //添加到缓存集合中
            returnList.add(new AllowanceCache(user_id, rate));
            //移除当前角色（已结算）
//            iterator.remove();
        }
        //环节推进
        return caseEntity;
    }




    /**
     * 添加用户金额
     *
     * @param user_id  要加钱的用户ID
     * @param addMoney 要的钱数量
     * @return
     */
    public boolean addMoney(int user_id, double addMoney) {
        //TODO 调用用户修改金额接口
        return true;
    }

}
