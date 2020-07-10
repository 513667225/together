package service;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.together.AppUser;
import com.together.modules.user.entity.UserEntity;
import com.together.modules.user.service.IUserService;
import com.together.modules.user.timing.UserRelationDepue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={AppUser.class})// 指定启动类
public class GoodsServiceImplTest {
    @Autowired
    IUserService iUserService;


    @Test
    public void testUserInsert() throws Exception {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                if(UserRelationDepue.linkedBlockingQueue.isEmpty()){
                    return;
                }
                String relation = UserRelationDepue.linkedBlockingQueue.poll();
                String[] split = relation.split(",");
//                Collections.reverse(Arrays.asList(split));
                List<UserEntity> userEntities=iUserService.selectUserByids(split);
                List<UserEntity> list=new ArrayList<>();

                Map<String,UserEntity> map=new HashMap<>();
                for (UserEntity userEntity : userEntities) {
                    map.put(userEntity.getUserId()+"",userEntity);
                }
                for (int i = 0; i < userEntities.size(); i++) {
                    //修改直邀人数+1
                    UserEntity userEntity = userEntities.get(i);
                    System.err.println(userEntities);
                    if(i==0){
                        userEntity.setUnderlingSize(userEntity.getUnderlingSize()+1);
                    }
                    isUpdateMessage(userEntity,list,map);
                }
                System.err.println(list);
                for (UserEntity userEntity : list) {
                    iUserService.getBaseMapper().updateById(userEntity);
                }
            }
        }, 1000, 1000);
        for (int i = 0; i <1; i++) {
            Integer userReferrer=10271;
            UserEntity userEntity=new UserEntity();
            userEntity.setUserNickname("癫子i");
            userEntity.setMajordomoSize(0);
            userEntity.setManagerSize(0);
            userEntity.setMemberSize(0);
            userEntity.setTeammanagerSize(0);
            userEntity.setGoupSize(0);
            userEntity.setUnderlingSize(0);
            userEntity.setUserLevel(0);//0普通人  1会员 2经理 3总监
            if(userReferrer!=null){
                userEntity.setUserReferrer(userReferrer);//  推荐人id
                //维护顶层推荐人
                UserEntity userReferrerentity = iUserService.getBaseMapper().selectById(userReferrer);
                if(userReferrerentity!=null&&userReferrerentity.getTopRefereeId()!=null){
                    userEntity.setTopRefereeId(userReferrerentity.getTopRefereeId());
                }else{
                    userEntity.setTopRefereeId(userReferrer);
                }
                //维护所有上层人员ID
                if(userReferrerentity!=null&&userReferrerentity.getRelation()!=null){
                    userEntity.setRelation(userReferrerentity.getRelation()+","+userReferrerentity.getUserId());
                }else{
                    userEntity.setRelation(userReferrerentity.getRelation()+"");
                }
            }
            iUserService.getBaseMapper().insert(userEntity);
            //TODO: 如果被推荐人id为不空，给推荐人+邀请人加一，看是否需要改变用户等级
            if(userReferrer!=null){
                //加入队列处理
                UserRelationDepue.linkedBlockingQueue.add(userEntity.getRelation());
            }
        }
        System.in.read();
    }


    //修改推荐人及上层用户 直接邀请人数及团队邀请人数，判断是否满足升级条件
    private void isUpdateMessage(UserEntity userEntity,List<UserEntity> list,Map<String,UserEntity> map) {
        //修改团队人数+1
        userEntity.setGoupSize(userEntity.getGoupSize()+1);
        //判断是否需要更新用户等级
        isUpdateUserLevel(userEntity,map);
        list.add(userEntity);
    }

    //判断用户级别需要要修改
    public Integer isUpdateUserLevel(UserEntity userEntity,Map<String,UserEntity> map){
        int identification=-1;
        if(userEntity.getUserLevel()==0
                &&userEntity.getUnderlingSize()>=10
                &&userEntity.getGoupSize()>=20){
            //满足会员条件  修改用户推荐人等级
            userEntity.setUserLevel(1);
            //修改上层所有人团队会员+1
            String relation = userEntity.getRelation();
            String[] split = relation.split(",");
            Collections.reverse(Arrays.asList(split));
            for (int i = 0; i < split.length; i++) {
                UserEntity userEntity1 = map.get(split[i]);
                userEntity1.setTeamMember(userEntity1.getTeamMember()+1);
                //直接会员人数+1
                if(i==0){
                    userEntity1.setMemberSize(userEntity1.getMemberSize()+1);
                }
            }
            identification=1;
        }
        if(userEntity.getUserLevel()==1
                &&userEntity.getGoupSize()>=30
                &&userEntity.getMemberSize()>=10){
            //满足经理条件  修改用户推荐人等级
            userEntity.setUserLevel(2);
            //修改上层所有人团队经理+1
            String relation = userEntity.getRelation();
            String[] split = relation.split(",");
            Collections.reverse(Arrays.asList(split));
            for (int i = 0; i < split.length; i++) {
                UserEntity userEntity1 = map.get(split[i]);
                userEntity1.setTeammanagerSize(userEntity1.getTeammanagerSize()+1);
                //直接经理人数+1
                if(i==0){
                    userEntity1.setManagerSize(userEntity1.getManagerSize()+1);
                }
            }
        }
        if(userEntity.getUserLevel()==2
                &&userEntity.getTeammanagerSize()>=30
                &&userEntity.getMajordomoSize()>=15){
            //满足总监条件  修改用户推荐人等级
            userEntity.setUserLevel(3);
            //修改上层所有人团队总监+1
            String relation = userEntity.getRelation();
            String[] split = relation.split(",");
            Collections.reverse(Arrays.asList(split));
            for (int i = 0; i < split.length; i++) {
                UserEntity userEntity1 = map.get(split[i]);
                userEntity1.setTeamMajordomo(userEntity1.getTeamMajordomo()+1);
                //直接总监人数+1
                if(i==0){
                    userEntity1.setMajordomoSize(userEntity1.getMajordomoSize()+1);
                }
            }
            identification=3;
        }
        return identification;
    }


    @Test
    public void queryAllGoods() throws Exception {

        String str="1111";
        System.out.println(str.split(",")[0]);

        Map<String, UserEntity> map=new HashMap<>();
        map.put("1111",new UserEntity());
        map.put("2222",new UserEntity());
        Set<Map.Entry<String, UserEntity>> entries = map.entrySet();
        for (Map.Entry<String, UserEntity> entry : entries) {
            String key = entry.getKey();
            UserEntity value = entry.getValue();
            System.out.println(key+"----"+value);
        }
        UserEntity userEntity = map.get("1111");
        userEntity.setUserNickname("谈加薪");
        Set<Map.Entry<String, UserEntity>> entries2 = map.entrySet();
        for (Map.Entry<String, UserEntity> entry : entries2) {
            String key = entry.getKey();
            UserEntity value = entry.getValue();
            System.out.println(key+"----"+value);
        }


//        List<UserEntity> userEntities = iGoodsService.selectUserByids(new String[]{
//                "10236","10241","10242"
//        });
//        System.out.println(userEntities);
//        P p = new P();
//        p.put("referrer_id",10235);
//        p.put("x",0);
//        p.put("n","普通新");
//        iGoodsService.test(p);
    }



}
