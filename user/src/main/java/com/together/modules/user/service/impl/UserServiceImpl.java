package com.together.modules.user.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.together.modules.user.entity.UserEntity;
import com.together.modules.user.mapper.UserMapper;
import com.together.modules.user.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.together.modules.user.utli.*;
import com.together.util.P;
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

import java.math.BigDecimal;
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




    public static Integer CorrectYuan2Fen(String  yuan) {
        //TODO：（重点）Double直接转BigDecimal丢失精度，此处需要将Double转换为String
        return new BigDecimal(String.valueOf(yuan)).movePointRight(2).intValue();
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
                return userEntity;
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

    @Override
    public synchronized JSONObject payGroupOrder(Map<String, Object> param) {
        try {
            JSONObject json = new JSONObject();
            String orderId = "394938294";
            //TODO： 参数组装
            SortedMap<String, Object> paramMap = new TreeMap<>();
            //TODO： 随机数
            String randoms = Math.random() * 10 + System.currentTimeMillis() + Math.random() * 10 + "";
            //TODO： 订单号
            String out_trade_no = RandomStringGeneratorId.getRandomStringByLength(32);
            //TODO：ip
            String spbill_create_ip = ((P)param).getRequest().getRemoteAddr();
//            String spbill_create_ip = "192.168.0.111";
            //TODO：商品描述
//            String body = "爱尚拼团（小程序拼单）";
            String body = "foakspokas";
            //TODO：金额
//            String money = (String) param.get("money");
            String money ="0.01";
            Integer cmoney = CorrectYuan2Fen(money);
            System.out.println("正确的元转分结果：" + cmoney);

            //TODO：用户id
//            String id = (String) param.get("userId");
            String id = "10002";
            //TODO： 根据用户id传openid
            UserEntity userEntity = baseMapper.selectOne(new QueryWrapper<UserEntity>().eq("user_id",id));
            if (userEntity == null) {
                json.put("msg", "没有这个用户");
                return json;
            }
            String openid = userEntity.getWeixinOpenid();
            paramMap.put("appid", PayConstants.WX_APPID);                        // appid
            paramMap.put("mch_id", PayConstants.WX_PARTNER);                    // secret
            paramMap.put("nonce_str", randoms);                                 // 随机数
            paramMap.put("body", body);                                         // 商品描述
            paramMap.put("device_info", id);                                   // 用户id
            paramMap.put("out_trade_no", out_trade_no);                         // 流水号
            paramMap.put("total_fee", cmoney + "");                                // 金额	(单位：分)
            paramMap.put("spbill_create_ip", spbill_create_ip);                 // IP地址
            paramMap.put("notify_url", PayConstants.WX_NOTIFY_PAYGROUP_URL);        // 回调通知地址
            paramMap.put("trade_type", "JSAPI");                                  // 交易类型
            paramMap.put("attach", orderId);                                         //订单id
//            paramMap.put("openid", openid);                                        // 前面 通过 code 取到的 openid (用户标识)
            paramMap.put("sign", PayService.createSign(paramMap));                // 签名

            String paramXml = XmlAndMap.toXml(paramMap);                            // paramMap -> paramXml
            String prepayid = PayService.getPrepayId(paramXml);                     // paramXml -> 预支付交易会话标识 prepare_id

            SortedMap<String, Object> finalMap = new TreeMap<String, Object>();
            finalMap.put("appId", PayConstants.WX_APPID);
            finalMap.put("timeStamp", System.currentTimeMillis() / 1000 + "");
            finalMap.put("nonceStr", paramMap.get("nonce_str"));
            finalMap.put("package", "prepay_id=" + prepayid);
            finalMap.put("signType", "MD5");
            String finalsign = PayService.createSign(finalMap);                    // 支付签名

            json.put("timeStamp", finalMap.get("timeStamp"));
            json.put("nonceStr", finalMap.get("nonceStr"));
            json.put("package", finalMap.get("package"));
            json.put("signType", "MD5");
            json.put("paySign", finalsign);

            return json;

        } catch (Exception e) {
            log.error("团购订单继续支付下单出错:{}",e);
        }
        return null;
    }

    @Override
    public Map<String, Object> getGroupUserState(P p) {
        System.out.println(p);
        return ResponseUtli.NullToMap();
    }


}
