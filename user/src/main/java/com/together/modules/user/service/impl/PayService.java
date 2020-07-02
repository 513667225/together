package com.together.modules.user.service.impl;

import com.together.modules.user.http.HttpClientConnectionManager;
import com.together.modules.user.utli.PayConstants;
import com.together.modules.user.utli.XmlAndMap;
import com.together.modules.user.utli.md5.MD5Util;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;


@Service
public class PayService {
	
	public static DefaultHttpClient httpclient;

	static  {
		httpclient = new DefaultHttpClient();
		httpclient = (DefaultHttpClient) HttpClientConnectionManager.getSSLInstance(httpclient);
	}

	
	/**
	 * 创建签名  md5摘要,规则是:按参数名称a-z排序,遇到空值的参数不参加签名。
	 */
	public static String createSign(SortedMap<String, Object> paramMap) {
		StringBuffer sb = new StringBuffer();
		Set es = paramMap.entrySet();
		Iterator it = es.iterator();
		
		while (it.hasNext()) {
			
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			
			if (null != v && !"".equals(v) && !"sign".equals(k) && !"key".equals(k)) {
				sb.append(k + "=" + v + "&");
			}
		}
		sb.append("key=" + PayConstants.WX_PARTNERKEY);
		String sign = MD5Util.MD5Encode(sb.toString(), "UTF-8").toUpperCase();
		return sign;
	}
	

	/**
	 * 获取 prepayid
	 * @param paramXml
	 * @return
	 */
	public static String getPrepayId(String paramXml) {
		DefaultHttpClient client = new DefaultHttpClient();
		client.getParams().setParameter(ClientPNames.ALLOW_CIRCULAR_REDIRECTS, true);
		HttpPost httpost = HttpClientConnectionManager.getPostMethod(PayConstants.WX_PAY_URL);
		String prepayid = "";
	    try {
	    	httpost.setEntity(new StringEntity(paramXml, "UTF-8"));
			HttpResponse response = httpclient.execute(httpost);
		    String jsonStr = EntityUtils.toString(response.getEntity(), "UTF-8");
			System.out.println(jsonStr);
			if(jsonStr.indexOf("FAIL") != -1){
		    	return prepayid;
		    }
		    Map<String,Object> map = XmlAndMap.toMap(jsonStr);
		    String return_code  = (String) map.get("return_code");
		    System.out.println("PayService.class, getPrepayId(), return_code=" + return_code);
		    prepayid  = (String) map.get("prepay_id");
		} catch (Exception e) {
			e.printStackTrace();
		}
	    return prepayid;
	}
	
	
	/**
	 * 返回结果给微信
	 * @param result 
	 * @return
	 */
	public static String returnResult(boolean result) {
		return result ? 
				"<xml>"
				+ 	"<return_code><![CDATA[SUCCESS]]></return_code>"
				+ 	"<return_msg><![CDATA[OK]]></return_msg>"
				+"</xml>"  
				:
				"<xml>"
				+ 	"<return_code><![CDATA[FAIL]]></return_code>"
				+ 	"<return_msg><![CDATA[NO]]></return_msg>"
				+"</xml>";
	}
}
