package com.together.template;

import com.alibaba.fastjson.JSONObject;
import com.together.entity.UserEntity;
import com.together.serviceClient.UserServiceClient;
import com.together.util.P;
import com.together.util.utli.PayConstants;
import com.together.utli.RandomStringGeneratorId;
import com.together.utli.XmlAndMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

@Component
public class PaymentTemplate {

    @Autowired
    UserServiceClient userServiceClient;

    public JSONObject payment(Map<String, Object> param) {
        try {
            JSONObject json = new JSONObject();
            String orderId = "394938294";  //订单ID
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
            UserEntity userEntity = userServiceClient.getUserById(id).thisToEntity(UserEntity.class);
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
            paramMap.put("openid", openid);                                        // 前面 通过 code 取到的 openid (用户标识)
//            paramMap.put("sign", PayService.createSign(paramMap));                // 签名
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
//            log.error("团购订单继续支付下单出错:{}",e);
        }
        return null;
    }


    public static Integer CorrectYuan2Fen(String  yuan) {
        //TODO：（重点）Double直接转BigDecimal丢失精度，此处需要将Double转换为String
        return new BigDecimal(String.valueOf(yuan)).movePointRight(2).intValue();
    }

}
