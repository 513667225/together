package com.together.modules.user.utli;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

@Component
public class HttpUtli {
        private final  static String APPID="wx97cc5113d8c1a92b";
        private final  static String SECRET="40565b235c6ff677b53556a5517088d8";
        private final  static String GRANT_TYPE="client_credential";

        private final  static String ACCESS_TOKEN_URL="https://api.weixin.qq.com/cgi-bin/token?grant_type="+GRANT_TYPE+"&appid="+APPID+"&secret="+SECRET;

        public static Object getAccessToken(){
            //ClientHttpRequestFactory
            SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
            requestFactory.setConnectTimeout(30000);// 设置超时
            requestFactory.setReadTimeout(30000);

            //利用复杂构造器可以实现超时设置,内部实际实现为 HttpClient
            RestTemplate restTemplate = new RestTemplate(requestFactory);

            ResponseEntity<String> exchange = null;
            try{
                exchange= restTemplate.exchange(ACCESS_TOKEN_URL, HttpMethod.GET, null, String.class);
            }catch (ResourceAccessException e){

            }
            if (exchange != null && exchange.getStatusCode() == HttpStatus.OK) {
                String body=exchange.getBody();
                JSONObject parseObject = JSON.parseObject(body);
                String access_token=parseObject.get("access_token").toString();
                return  access_token;
            }
            return null;
        }
}
