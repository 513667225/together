package com.together.modules.spell.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.together.entity.Spell;
import com.together.enun.GoodsLevel;
import com.together.enun.TogetherNumber;
import com.together.modules.spell.service.SpellService;
import com.together.parameter.MqParameter;
import com.together.parameter.SyncLock;
import com.together.parameter.SystemParameter;
import com.together.util.MqUtil;
import com.together.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.ReturnType;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class SpellServiceImpl implements SpellService {


    @Autowired
    private MqUtil mqUtil;

//    private  Object object =

    @Autowired
    private SetOperations setOperations;
    private ReentrantLock reentrantLock = new ReentrantLock(true);
    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public R startTogether(Integer userId, Integer goodsId, TogetherNumber togetherNumber, GoodsLevel goodsLevel) {
//        mqUtil.testSend();
//        Spell spell = new Spell(userId, goodsId, togetherNumber,goodsLevel);
//        = 1L;
//        try {
//            reentrantLock.lock();
//        synchronized (SyncLock.spellLock){
//        String s = JSON.toJSONString(spell);
        Long size = (Long) redisTemplate.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection redisConnection) throws DataAccessException {
                Random random = new Random();
//                redisConnection.openPipeline();
                return redisConnection.eval(("if redis.call(\"sadd\",KEYS[1],ARGV[1])==1 then\n" +
                        "    return redis.call(\"scard\",KEYS[1])\n" +
                        "else \n" +
                        "   return 0\n" +
                        "end   ").getBytes(), ReturnType.fromJavaType(Long.class), 1, SystemParameter.i.toString().getBytes(), (random.nextInt(111111111)+"").getBytes());

            }
        });
//            setOperations.add(SystemParameter.i.toString(), spell);
//            Long size  = setOperations.size(SystemParameter.i.toString());
//        }
            System.out.println("当前排队团号:" + SystemParameter.i.get() + "排队人数" + size);
            if (size > 99) {
                System.out.println("第" + SystemParameter.i.get() + "开拼");
                mqUtil.testSend("directExchange", "direct.key", SystemParameter.i.get());
                SystemParameter.i.addAndGet(1);
            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            reentrantLock.unlock();
//        }
        return null;
    }


    @Override
    public R startTogetherMq(int userId, int goodsId, TogetherNumber togetherNumber, GoodsLevel goodsLevel) {
        Spell spell = new Spell(userId, goodsId, togetherNumber,goodsLevel);
        mqUtil.testSend(MqParameter.CREATE_GROUP_EXCHANGE_NAME, MqParameter.CREATE_GROUP_EXCHANGE_KEY_NAME, JSON.toJSONString(spell));
        return null;
    }
}
