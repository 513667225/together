package com.test;

import com.together.entity.AllowanceCache;
import com.together.entity.CaseEntity;
import com.together.entity.UserSuperstratumRelationDo;
import com.together.enun.GoodsLevel;
import com.together.enun.UserRelationshipCase;
import org.springframework.data.redis.connection.ReturnType;
import org.springframework.web.client.RestTemplate;
import redis.clients.jedis.Jedis;

import java.util.*;
import java.util.concurrent.CountDownLatch;

public class HttpTest {


    public static void delKey() throws Exception{
        Jedis jedis=new Jedis("47.114.38.198",6379);
        jedis.flushAll();
    }

    public static void main(String[] args) throws Exception{
//        delKey();
//        testThread();
//        list(2,true);
        LinkedList linkedList = new LinkedList();
        UserSuperstratumRelationDo userSuperstratumRelationDo = new UserSuperstratumRelationDo();
        userSuperstratumRelationDo.setUser_id(1);
        userSuperstratumRelationDo.setUserLevel(2);
        linkedList.add(userSuperstratumRelationDo);

        UserSuperstratumRelationDo userSuperstratumRelationDo1 = new UserSuperstratumRelationDo();
        userSuperstratumRelationDo1.setUser_id(2);
        userSuperstratumRelationDo1.setUserLevel(2);
        linkedList.add(userSuperstratumRelationDo1);

        UserSuperstratumRelationDo userSuperstratumRelationDo5 = new UserSuperstratumRelationDo();
        userSuperstratumRelationDo5.setUser_id(5);
        userSuperstratumRelationDo5.setUserLevel(3);
        linkedList.add(userSuperstratumRelationDo5);

        UserSuperstratumRelationDo userSuperstratumRelationDo2 = new UserSuperstratumRelationDo();
        userSuperstratumRelationDo2.setUser_id(3);
        userSuperstratumRelationDo2.setUserLevel(3);
            linkedList.add(userSuperstratumRelationDo2);

        UserSuperstratumRelationDo userSuperstratumRelationDo3 = new UserSuperstratumRelationDo();
        userSuperstratumRelationDo3.setUser_id(4);
        userSuperstratumRelationDo3.setUserLevel(3);
        linkedList.add(userSuperstratumRelationDo3);

        UserSuperstratumRelationDo userSuperstratumRelationDo4 = new UserSuperstratumRelationDo();
        userSuperstratumRelationDo4.setUser_id(4);
        userSuperstratumRelationDo4.setUserLevel(3);
        linkedList.add(userSuperstratumRelationDo4);




        getUserForNeedAllowance(linkedList.iterator(),null,new CaseEntity(UserRelationshipCase.CASE1,true,true),GoodsLevel.ONE);

    }

    public static List getUserForNeedAllowance(Iterator<UserSuperstratumRelationDo> iterator, List<AllowanceCache> returnList, CaseEntity caseEntity, GoodsLevel goodsLevel) {
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
    public static CaseEntity userRelationshipCase(UserSuperstratumRelationDo next, List<AllowanceCache> returnList, CaseEntity caseEntity, GoodsLevel goodsLevel) {
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

    public  static void addMoney(int user_id,double moneyNum){
        System.out.println("用户id："+user_id+"得到的分成:+"+moneyNum);
    }




    public static  void list(int i,Boolean f){
        List l = new LinkedList();
        l.add(2);
        l.add(2);
        l.add(3);
        l.add(3);
        Iterator iterator = l.iterator();
        while (iterator.hasNext()&&f) {
            Object next = iterator.next();
            switch (i){
                case 2:
                    System.out.println(2);
                    iterator.remove();
                    list(3,true);
                    break;
                case 3:
                    System.out.println(3);
                    iterator.remove();
                    f = false;
                  break;

            }

//            System.out.println(l.size());


        }


    }

    public static void testThread(){
        RestTemplate restTemplate = new RestTemplate();
        CountDownLatch downLatch = new CountDownLatch(1);

        for (int i = 0; i <57 ; i++) {
            Thread thread = new Thread(){
                @Override
                public void run() {
                    try {
                        downLatch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Random random = new Random();
                    int i = random.nextInt(11111111);
//                    System.out.println(i);
                    restTemplate.getForObject("http://localhost:2001/spell/together?userId="+i+"&goodsId=1",Object.class);

                }
            };
            thread.start();
        }
        downLatch.countDown();
    }

}
