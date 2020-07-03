package com.together.modules.user.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.together.modules.user.entity.UserEntity;
import com.together.modules.user.mapper.UserMapper;
import com.together.modules.user.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.together.util.P;
import com.together.util.utli.PayConstants;
import com.together.util.utli.RedisIdUtil;
import com.together.util.utli.ResponseUtli;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.*;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author 
 * @since 2020-06-28
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements IUserService {


    @Autowired
    private ValueOperations valueOperations;

    @Override
    public UserEntity getUserByName(P p) {
        String userName = (String) p.get("userName");
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("userName",userName);
        //baseMapper.selectOne(wrapper)
        return null;
    }





    /**
     * 小程序登录
     * @param param code 名称 头像
     * @return 用户对象
     */
    @Transactional
    public UserEntity getUserLogin(Map<String,Object> param) {
        try {
            //code 名称 头像
            String code = (String) param.get("code");
            String userName =(String) param.get("userName");
            String avatarUrl = (String) param.get("avatarUrl");
            Integer userReferrer=(Integer) param.get("userReferrer");

            Map<String, Object> map = new HashMap<String, Object>();
            String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + PayConstants.WX_APPID + "&secret=" + PayConstants.WX_SECRET + "&js_code=" + code + "&grant_type=authorization_code";

            //ClientHttpRequestFactory
            SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
            requestFactory.setConnectTimeout(30000);// 设置超时
            requestFactory.setReadTimeout(30000);

            //利用复杂构造器可以实现超时设置,内部实际实现为 HttpClient
            RestTemplate restTemplate = new RestTemplate(requestFactory);

            ResponseEntity<String> exchange = null;
            try{
                exchange= restTemplate.exchange(url, HttpMethod.GET, null, String.class);
            }catch (ResourceAccessException e){
                log.error("请求微信超时,请重试");
            }
            if (exchange != null && exchange.getStatusCode() == HttpStatus.OK) {
                String body = exchange.getBody();
                JSONObject parseObject = JSONObject.parseObject(body);
                Set<Map.Entry<String, Object>> entrySet = parseObject.entrySet();
                for (Map.Entry<String, Object> entry : entrySet) {
                    map.put(entry.getKey(), entry.getValue().toString());
                }
            }
            QueryWrapper<UserEntity> queryWrapper =  new QueryWrapper();
            queryWrapper.eq("weixin_openid",map.get("openid").toString());
            //TODO: 查看用户是否登录过小程序,登录则直接返回,否则就插入新用户'
            UserEntity userEntity = baseMapper.selectOne(queryWrapper);
            if (userEntity == null) {
                userEntity=new UserEntity();
                Date date = new Date();
                userEntity.setWeixinOpenid(map.get("openid").toString());
                userEntity.setAddTime(date);
                userEntity.setUpdateTime(date);
                userEntity.setUserLastLoginTime(date);
                userEntity.setUserId(RedisIdUtil.nextId("userId"));
                userEntity.setUserLevel(0);
                userEntity.setUserNickname(userName);
                userEntity.setUserAvatar(avatarUrl);
                userEntity.setUserStatus(0);
                if(userReferrer!=null){
                    userEntity.setUserReferrer(userReferrer);//  推荐人id
                }
                baseMapper.insert(userEntity);
                //TODO: 如果被推荐人id为不空，给推荐人+邀请人加一，看是否需要改变用户等级
                if(userReferrer!=null){
                    //修改推荐人及上层用户 直接邀请人数及团队邀请人数，判断是否满足升级条件
                    isUpdateMessage(userReferrer,true);
                }
            }
           //TODO: 如果登录进来的 头像/名称 与数据库保存的不同,则进行修改
            if(!userEntity.getUserAvatar().equals(avatarUrl) || !userEntity.getUserNickname().equals(userName)){
                int updateUser = baseMapper.updateById(userEntity);
                if(updateUser == 0){
                    throw new RuntimeException();
                }
            }
            //TODO: 保存session_key
            valueOperations.set(String.valueOf(userEntity.getUserId()),map.get("session_key"));
            return userEntity;
        } catch (Exception e) {
            log.error("用户登录出错:{}",e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return null;
    }

    //修改推荐人及上层用户 直接邀请人数及团队邀请人数，判断是否满足升级条件
    private void isUpdateMessage(Integer userReferrer,boolean isUnderling) {
        UserEntity userEntity1 = baseMapper.selectById(userReferrer);
        if(isUnderling){
            //修改直邀人数+1
            userEntity1.setUnderlingSize(userEntity1.getUnderlingSize()+1);
        }
        //修改团队人数+1
        userEntity1.setGoupSize(userEntity1.getGoupSize()+1);
        //判断是否需要更新用户上级等级
        isUpdateUserLevel(userEntity1);
        baseMapper.updateById(userEntity1);
        if(userEntity1.getUserReferrer()!=null) {
            //递归修改上级团队人数
            isUpdateMessage(userEntity1.getUserReferrer(),false);
        }
        return;
    }


    //判断用户级别需要要修改
    public Integer isUpdateUserLevel(UserEntity userEntity){
        int identification=-1;
        if(userEntity.getUnderlingSize()>=10
                &&userEntity.getGoupSize()>=20
                &&userEntity.getUserLevel()==0){
            //满足会员条件  修改用户推荐人等级
            userEntity.setUserLevel(1);
            identification=1;
        }
        if(userEntity.getMemberSize()>=10
                &&userEntity.getGoupSize()>=30
                &&userEntity.getUserLevel()==1){
            //满足会员条件  修改用户推荐人等级
            userEntity.setUserLevel(2);
            identification=2;
        }
        if(userEntity.getMajordomoSize()>=15
                &&userEntity.getTeammanagerSize()>=30
                &&userEntity.getUserLevel()==2){
            //满足会员条件  修改用户推荐人等级
            userEntity.setUserLevel(3);
            identification=3;
        }
        baseMapper.updateById(userEntity);
        return identification;
    }

    //判断用户级别是否要修改为服务经理
//    public UserEntity isUpdateManager(UserEntity userEntity){
//        if(userEntity.getMemberSize()>=10
//                &&userEntity.getGoupSize()>=30
//                &&userEntity.getUserLevel()==1){
//            //满足会员条件  修改用户推荐人等级
//            userEntity.setUserLevel(2);
//            baseMapper.updateById(userEntity);
//            Integer userReferrer = userEntity.getUserReferrer();
//            UserEntity userEntity1 = baseMapper.selectById(userReferrer);
//            UserEntity updateTeammanager = isUpdateTeammanager(userEntity1);
//            isUpdateMajordomo(updateTeammanager);
//
//        }
//        return userEntity;
//    }
//
//    //判断用户级别是否要修改为服务总监
//    public void isUpdateMajordomo(UserEntity userEntity){
//        if(userEntity.getMajordomoSize()>=15
//                &&userEntity.getTeammanagerSize()>=30
//                &&userEntity.getUserLevel()==2){
//            //满足会员条件  修改用户推荐人等级
//            userEntity.setUserLevel(3);
//            baseMapper.updateById(userEntity);
//        }
//    }
//
//    //修改团队服务经理人数
//    public UserEntity isUpdateTeammanager(UserEntity userEntity){
//        userEntity.setTeammanagerSize(userEntity.getTeammanagerSize()+1);
//        baseMapper.updateById(userEntity);
//        return userEntity;
//    }

    //递归修改用户等级


    //递归修改团队人数
    public void isUpdateGoup(UserEntity userEntity){
        userEntity.setGoupSize(userEntity.getGoupSize()+1);
        //修改团队人数+1  判断团队人数+1后  能否升级
        Integer updateUserLevel = isUpdateUserLevel(userEntity);
        UserEntity userEntity1 = baseMapper.selectById(userEntity.getUserReferrer());
        if(userEntity1.getUserReferrer()!=null){
            isUpdateGoup(userEntity1);
        }
        if(updateUserLevel!=-1){
            isUpdateUserLevel(userEntity1);
        }
        return;
    }

    //修改团队人数
    public void isUpdateGoup(Integer userReferrerId){
        if(userReferrerId!=null){
            UserEntity userEntity1 = baseMapper.selectById(userReferrerId);
            isUpdateGoup(userEntity1);
        }
        return;
    }


    @Override
    public Map<String, Object> getGroupUserState(P p) {
        System.out.println(p);
        return ResponseUtli.NullToMap();
    }

    @Override
    public void test(P p) {
        UserEntity userEntity = baseMapper.selectById(10002);
        for (int i = 1; i <= p.getInt("size"); i++) {
            userEntity.setUserId(i);
            userEntity.setUserReferrer(p.getInt("referrer_id"));
            baseMapper.insert(userEntity);
            //TODO: 如果被推荐人id为不空，给推荐人+邀请人加一，看是否需要改变用户等级
            if(userEntity.getUserReferrer()!=null){
                //修改推荐人及上层用户 直接邀请人数及团队邀请人数，判断是否满足升级条件
                isUpdateMessage(userEntity.getUserReferrer(),true);
            }
        }
    }


}
