package com.together.modules.user.timing;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.together.modules.user.entity.UserEntity;
import com.together.modules.user.entity.UserReferrerDo;
import com.together.modules.user.entity.UserSuperstratumRelationDo;
import com.together.modules.user.mapper.UserMapper;
import com.together.modules.user.service.IUserService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

@Component
public class ExecuteUserRelationDepue extends ServiceImpl<UserMapper, UserEntity> implements InitializingBean{



    @Override
    public void afterPropertiesSet() throws Exception {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                if(UserRelationDepue.linkedBlockingQueue.isEmpty()){
                    return;
                }
                Integer poll = UserRelationDepue.linkedBlockingQueue.poll();
                List<UserReferrerDo> userReferrerDos = baseMapper.selectByuserReferrerId(poll);
                List<UserReferrerDo> userReferrerDoCopy=new ArrayList<>();
                UserReferrerDorecursion(userReferrerDos,userReferrerDoCopy);
                System.err.println(userReferrerDoCopy.size());
                UserRelationDepue.linkedBlockingQueue.remove(poll);
            }
        }, 1000, 1000);
    }

    public void UserReferrerDorecursion(List<UserReferrerDo> userReferrerDos,List<UserReferrerDo> userReferrerDoCopy){
        if(userReferrerDos!=null) {
            for (UserReferrerDo referrerDo : userReferrerDos) {
                UserReferrerDorecursion(referrerDo.getUserReferrerDos(),userReferrerDoCopy);
                referrerDo.setUserReferrerDos(null);
                userReferrerDoCopy.add(referrerDo);
            }
        }
    }

    public void UserReferrerDorecursion(UserReferrerDo userReferrerDo){
        List<UserReferrerDo> userReferrerDos=new ArrayList<>();
        Integer user_referrer = userReferrerDo.getUser_referrer();
        if(user_referrer!=null){
//            UserReferrerDo userReferrerDo1 = userReferrerDos.get(user_referrer);  //取出推荐人
            ArrayList<UserSuperstratumRelationDo> userSuperstratumRelationDos = selectUserSuperstratum(new ArrayList<>(), user_referrer);
            System.out.println(userSuperstratumRelationDos);
        }
    }


    //根据用户id  查询上层所有经理或者总监级别以上
    public ArrayList<UserSuperstratumRelationDo> selectUserSuperstratum(ArrayList<UserSuperstratumRelationDo> userSuperstratumRelationDos,Integer user_id){
        UserSuperstratumRelationDo userSuperstratumRelationDo = baseMapper.selectUserSuperstratum(user_id);
        if(userSuperstratumRelationDo.getUserReferrer()!=null){
            if(userSuperstratumRelationDo.getUserLevel()==2 || userSuperstratumRelationDo.getUserLevel()==3){
                userSuperstratumRelationDos.add(userSuperstratumRelationDo);
            }
            selectUserSuperstratum(userSuperstratumRelationDos,userSuperstratumRelationDo.getUserReferrer());
        }
        return userSuperstratumRelationDos;
    }

}
