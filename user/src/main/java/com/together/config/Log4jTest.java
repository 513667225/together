package com.together.config;

import org.apache.log4j.Logger;
import redis.clients.jedis.Jedis;

public class Log4jTest {
	private static Logger logger=Logger.getLogger(Log4jTest.class);
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		logger.error("error...");
		logger.info("info...");
		Jedis jedis=new Jedis("loveping.shop",6379);
		System.out.println(jedis.ping());
	}
 
}