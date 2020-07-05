package com.together.modules.user.utli;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.spec.AlgorithmParameterSpec;

public class Test {

    public String decrypt(String key, String iv, String encrypdata) throws Exception {
        AlgorithmParameterSpec ivSpec = new IvParameterSpec(Base64.decodeBase64(iv));
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec keySpec = new SecretKeySpec(Base64.decodeBase64(key), "AES");
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
        String result = new String(cipher.doFinal(Base64.decodeBase64(encrypdata)), StandardCharsets.UTF_8);
        JSONObject jsonObject = JSON.parseObject(result);
        //解析解密后的字符串  
        return (String) jsonObject.get("phoneNumber");
    }


}
