package com.together.modules.user.init;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Component
public class RedisInit implements InitializingBean {

    @Autowired
    JedisPool jedisPool;

    @Override
    public void afterPropertiesSet() throws Exception {
        Jedis jedis = jedisPool.getResource();
        jedis.setnx("userId", String.valueOf(10000));
        jedis.close();
    }
}
