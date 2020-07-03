package com.together.http;


import org.apache.http.conn.ssl.SSLSocketFactory;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

public class MySSLSocketFactory extends SSLSocketFactory{
	
	static{
		mySSLSocketFactory = new com.together.http.MySSLSocketFactory(createSContext());
	}
	
	private static com.together.http.MySSLSocketFactory mySSLSocketFactory = null;
	
	
	
	private static SSLContext createSContext(){
		SSLContext sslcontext = null;
		try {
			sslcontext = SSLContext.getInstance("SSL");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		try {
			sslcontext.init(null, new TrustManager[]{new TrustAnyTrustManager()}, null);
		} catch (KeyManagementException e) {
			e.printStackTrace();
			return null;
		}
		return sslcontext;
	}
	
	private MySSLSocketFactory(SSLContext sslContext) {
		super(sslContext);
		this.setHostnameVerifier(ALLOW_ALL_HOSTNAME_VERIFIER);
	}
	
	public static com.together.http.MySSLSocketFactory getInstance(){
		if(mySSLSocketFactory != null){
			return mySSLSocketFactory;
		}else{
			return mySSLSocketFactory = new com.together.http.MySSLSocketFactory(createSContext());
		}
	}
	
}