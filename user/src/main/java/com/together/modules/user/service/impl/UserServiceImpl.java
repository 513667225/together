package com.together.modules.user.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.together.enun.TipMsgEnum;
import com.together.modules.user.entity.UserEntity;
import com.together.entity.UserSuperstratumRelationDo;
import com.together.modules.user.entity.UserEntityDo;
import com.together.modules.user.entity.UserReferrerDo;
import com.together.modules.user.mapper.UserMapper;
import com.together.modules.user.service.IUserService;
import com.together.modules.user.timing.UserRelationDepue;
import com.together.modules.user.utli.HttpUtli;
import com.together.modules.user.utli.WxDecryptUtli;
import com.together.util.P;
import com.together.util.R;
import com.together.util.utli.PayConstants;
import com.together.util.utli.ResponseUtli;
import com.together.util.utli.ValidateUtli;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;
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
    private ValueOperations<String,Object> valueOperations;




    @Override
    public UserEntity getUserByName(P p) {
        String userName = (String) p.get("userName");
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("userName",userName);
        //baseMapper.selectOne(wrapper)
        return null;
    }

    // 算法名
    public static final String KEY_NAME = "AES";
    // 加解密算法/模式/填充方式
    // ECB模式只用密钥即可对数据进行加密解密，CBC模式需要添加一个iv
    public static final String CIPHER_ALGORITHM = "AES/CBC/PKCS7Padding";





    /**
     * 小程序登录
     * @param param code 名称 头像
     * @return 用户对象
     */
    @Transactional
    public UserEntity getUserLogin(Map<String,Object> param) throws Exception {
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
            QueryWrapper<UserEntity> queryWrapper =  new QueryWrapper<>();
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
                userEntity.setUserLevel(0);
                userEntity.setUserNickname(userName);
                userEntity.setUserAvatar(avatarUrl);
                userEntity.setUserStatus(0);
                userEntity.setTeamMajordomo(0);
                userEntity.setTeamMember(0);

                if(userReferrer!=null){
                    userEntity.setUserReferrer(userReferrer);//  推荐人id
                    //维护顶层推荐人
                    UserEntity userReferrerentity = baseMapper.selectById(userReferrer);
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
                baseMapper.insert(userEntity);
                //TODO: 如果被推荐人id为不空，给推荐人+邀请人加一，看是否需要改变用户等级
                if(userReferrer!=null){
                    //加入队列处理
                    UserRelationDepue.linkedBlockingQueue.add(userEntity.getRelation());
                }
            }
           //TODO: 如果登录进来的 头像/名称 与数据库保存的不同,则进行修改
            if(!userEntity.getUserAvatar().equals(avatarUrl) || !userEntity.getUserNickname().equals(userName)){
                int updateUser = baseMapper.updateById(userEntity);
                if(updateUser == 0){
                    throw new RuntimeException();
                }
            }
            System.out.println(map.get("session_key"));
            //TODO: 保存session_key
            valueOperations.set(String.valueOf(userEntity.getUserId()),map.get("session_key"));
            return userEntity;
        } catch (Exception e) {
            log.error("用户登录出错:{}",e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return null;
    }

    public void updateSessionKey(String code,String user_id) {
        try {
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
            valueOperations.set(user_id,map.get("session_key"));
        } catch (Exception e) {
            log.error("更新session_key出错:{}",e);
        }
    }



    @Override
    public Map<String, Object> selectUserReferrerTo(P p) throws Exception {
        Map<String,Object> map=new HashMap<>();
        UserEntity userEntity = baseMapper.selectById(p.getInt("user_id"));
        if(userEntity!=null){
            UserEntity userEntity2 = returnData(map, userEntity, "directReferrerId");
            if(userEntity2!=null){
                returnData(map, userEntity2, "managerReferrerId");
            }
        }
        return map;
    }

    public UserEntity returnData(Map<String,Object> map,UserEntity userEntity,String key){
        if(userEntity.getUserReferrer()!=null){
            UserEntity userEntity2 = baseMapper.selectById(userEntity.getUserReferrer());
            if(userEntity2.getUserLevel()==1||
                    userEntity2.getUserLevel()==2||
                    userEntity2.getUserLevel()==3){
                map.put(key,userEntity.getUserReferrer());
            }
            return userEntity2;
        }
        map.put(key,null);
        return null;
    }




    @Override
    public void test(P p) {
        UserEntity userEntity = new UserEntity();
//        UserRelationDepue.linkedBlockingQueue.add(userEntity.getUserId());
//        for (int i = p.getInt("start"); i <= (p.getInt("size")+p.getInt("start")); i++) {
//            userEntity.setUserId(""setUserId);userEntity.
        userEntity.setUserNickname(p.getString("n"));
            userEntity.setMajordomoSize(0);
            userEntity.setManagerSize(0);
            userEntity.setMemberSize(0);
            userEntity.setTeammanagerSize(0);
            userEntity.setGoupSize(0);
            userEntity.setUnderlingSize(0);
            userEntity.setUserReferrer(p.getInt("referrer_id"));
            userEntity.setUserLevel(p.getInt("x"));//0普通人  1会员 2经理 3总监
            baseMapper.insert(userEntity);
            //TODO: 如果被推荐人id为不空，给推荐人+邀请人加一，看是否需要改变用户等级
//            if(userEntity.getUserReferrer()!=null){
//                //修改推荐人及上层用户 直接邀请人数及团队邀请人数，判断是否满足升级条件
//                isUpdateMessage(userEntity.getUserReferrer(),true,-1,false);
//            }
//        }
    }


    @Override
    public R updateUserPhone(P p) throws Exception {
        String code = p.getString("code");
        String user_id = p.getString("user_id");
        if(code!=null&&user_id!=null){
            updateSessionKey(code,user_id);
        }else{
            try {
                ValidateUtli.validateParams(p,"user_id","encryptedData","iv");
            }catch (Exception e){
                throw new Exception(TipMsgEnum.PARAMETER_NULL_Excption.getMsg());
            }
        }
        String encryptedData = p.getString("encryptedData");
        String iv = p.getString("iv");
        Object o = valueOperations.get(user_id);
        if(o!=null){
            String sessionKey = String.valueOf(o);
            try {
                cn.hutool.json.JSONObject jsonObject = WxDecryptUtli.wxDecrypt(encryptedData, sessionKey, iv);
                Object phoneNumber = jsonObject.get("phoneNumber");
                if(phoneNumber!=null){
                    UserEntity userEntity=new UserEntity();
                    userEntity.setUserId(Integer.valueOf(user_id));
                    userEntity.setUserMobile(String.valueOf(phoneNumber));
                    baseMapper.updateById(userEntity);
                    return R.success("success");
                }
            }catch (Exception e){
                throw new Exception(TipMsgEnum.DECIPHERING_PHONE.getMsg());
            }
        }
        throw new Exception(TipMsgEnum.SESSIONKEY_STRLE.getMsg());
    }

    @Override
    public ArrayList<UserSuperstratumRelationDo> userReferrerDorecursion(P p) throws Exception {
        ValidateUtli.validateParams(p,"user_id");
        Integer user_id = p.getInt("user_id");
        if(user_id!=null){
//            UserReferrerDo userReferrerDo1 = userReferrerDos.get(user_referrer);  //取出推荐人
            ArrayList<UserSuperstratumRelationDo> userSuperstratumRelationDos = new ArrayList<>();
            selectUserSuperstratum(userSuperstratumRelationDos, user_id);
            return userSuperstratumRelationDos;
        }
        return null;
    }

    //根据用户id  查询上层所有经理或者总监级别以上
    public void selectUserSuperstratum(ArrayList<UserSuperstratumRelationDo> userSuperstratumRelationDos,Integer user_id){
        UserSuperstratumRelationDo userSuperstratumRelationDo = baseMapper.selectUserSuperstratum(user_id);
        if(userSuperstratumRelationDo==null){
            return;
        }
        if(userSuperstratumRelationDo.getUserLevel()==2 || userSuperstratumRelationDo.getUserLevel()==3){
            userSuperstratumRelationDos.add(userSuperstratumRelationDo);
        }
        if(userSuperstratumRelationDo.getUserReferrer()!=null){
            selectUserSuperstratum(userSuperstratumRelationDos,userSuperstratumRelationDo.getUserReferrer());
        }
    }

    @Autowired
    RestTemplate restTemplate;

    /***
     * TODO: 创建会员分享的二维码
     * TODO: @param path 扫码进入的小程序页面路径
     * TODO: @param lineColor 设置颜色
     * TODO: @param response
     */
    public void createCodeImag(String path, String user_id, HttpServletResponse response){
        try{
            String access_token = HttpUtli.getAccessToken().toString();
            response.setContentType("image/jpeg");
            String requestUrl="https://api.weixin.qq.com/wxa/getwxacode?access_token="+access_token;
            JSONObject object=new JSONObject();
            object.put("path",path);
            object.put("line_color",this.lineColor());
            object.put("scene",user_id);
            MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
            HttpEntity requestEntity = new HttpEntity(object, headers);
            ResponseEntity<byte[]> exchange = restTemplate.exchange(requestUrl, HttpMethod.POST, requestEntity, byte[].class);
            byte[] body = exchange.getBody();
            InputStream inputStream = new ByteArrayInputStream(body);
            OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
            //创建存放文件内容的数组
            byte[] buff =new byte[1024];
            //所读取的内容使用n来接收
            int n;
            //当没有读取完时,继续读取,循环
            while((n=inputStream.read(buff))!=-1){
                //将字节数组的数据全部写入到输出流中
                outputStream.write(buff,0,n);
            }
            //强制将缓存区的数据进行输出
            outputStream.flush();
            //关流
            outputStream.close();
            inputStream.close();
        }catch (Exception e){
            log.error("创建分享会员二维码失败:{}",e);
        }
    }

    @Override
    public void updateMoney(P p) throws Exception {
        Double shopping_gold1 = p.getDouble("shopping_gold");
        Double integral = p.getDouble("integral");
        Double spell_bean = p.getDouble("spell_bean");
        Double balance = p.getDouble("balance");

        if(shopping_gold1==null&&integral==null&&spell_bean==null&&balance==null){
            return;
        }
        UserEntity userEntity=new UserEntity();
        UpdateWrapper<UserEntity> userEntityUpdateWrapper = new UpdateWrapper<>();

        if(shopping_gold1!=null){
//            userEntity.setShopping_gold(shopping_gold1);
//            userEntityUpdateWrapper.eq("shopping_gold",shopping_gold1);
            userEntityUpdateWrapper.setSql("shopping_gold=shopping_gold+"+p.getBigDecimal("shopping_gold"));
        }
        if(integral!=null){
//            userEntity.setIntegral(integral);
//            userEntityUpdateWrapper.eq("integral",integral);
            userEntityUpdateWrapper.setSql("integral=integral+"+p.getBigDecimal("integral"));
        }
        if(spell_bean!=null){
//            userEntity.setSpell_bean(spell_bean);
//            userEntityUpdateWrapper.eq("spell_bean",spell_bean+);
            userEntityUpdateWrapper.setSql("spell_bean=spell_bean+"+p.getBigDecimal("spell_bean"));
        }
        if(balance!=null){
            userEntity.setBalance(balance);
            userEntityUpdateWrapper.setSql("balance=balance+"+p.getBigDecimal("balance"));
//            userEntityUpdateWrapper.eq("balance",balance);
        }
        try {
//            setSql("balance=balance+"+p.getBigDecimal("balance")).eq("admin_id",p.getInt("admin_id"));
            userEntityUpdateWrapper.eq("user_id",p.getInt("user_id"));
            update(userEntityUpdateWrapper);
        }catch (Exception e){
            throw new Exception(TipMsgEnum.UPDATE_MONRY_FAIL.getMsg());
        }
    }

    @Override
    public List<UserEntity> selectUserALlInviter(P p) {
        List<UserEntity> lists = baseMapper.selectUserALlInviter(p.getInt("user_id"));
        return lists;
    }

    @Override
    public List<UserEntity> selectUserByids(String[] ids) {
        return baseMapper.selectUserByids(ids);
    }

    /**
     * TODO:二维码颜色
     */
    public JSONObject lineColor() {
        Map<String,Object> map=new HashMap<>();
        map.put("r",255);
        map.put("g",0);
        map.put("b",0);
        JSONObject jsonObject=new JSONObject(map);
        return jsonObject;
    }

}
