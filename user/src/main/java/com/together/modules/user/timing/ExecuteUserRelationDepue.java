package com.together.modules.user.timing;

import com.alibaba.druid.sql.visitor.functions.Reverse;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.together.modules.user.entity.UserEntity;
import com.together.modules.user.entity.UserReferrerDo;
import com.together.entity.UserSuperstratumRelationDo;
import com.together.modules.user.mapper.UserMapper;
import com.together.modules.user.service.IUserService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.util.*;

//@Component
public class ExecuteUserRelationDepue extends ServiceImpl<UserMapper, UserEntity> implements InitializingBean{

    @Autowired
    private IUserService userService;


    @Override
    public void afterPropertiesSet() throws Exception {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                if(UserRelationDepue.linkedBlockingQueue.isEmpty()){
                    return;
                }
                String relation = UserRelationDepue.linkedBlockingQueue.poll();
                String[] split = relation.split(",");
                Collections.reverse(Arrays.asList(split));
                List<UserEntity> userEntities=userService.selectUserByids(split);

                List<UserEntity> list=new ArrayList<>();

                Map<String,UserEntity> map=new HashMap<>();
                for (UserEntity userEntity : userEntities) {
                    map.put(userEntity.getUserId()+"",userEntity);
                }
                for (int i = 0; i < userEntities.size(); i++) {
                    //修改直邀人数+1
                    UserEntity userEntity = userEntities.get(i);
                    if(i==0){
                        userEntity.setUnderlingSize(userEntity.getUnderlingSize()+1);
                    }
                    isUpdateMessage(userEntity,list,map);
                }
                userService.saveBatch(list);
            }
        }, 1000, 1000);
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
}
