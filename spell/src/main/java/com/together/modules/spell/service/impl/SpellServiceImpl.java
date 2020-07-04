package com.together.modules.spell.service.impl;

import com.alibaba.fastjson.JSON;
import com.together.entity.Spell;
import com.together.enun.TogetherNumber;
import com.together.modules.spell.service.SpellService;
import com.together.parameter.MqParameter;
import com.together.parameter.SystemParameter;
import com.together.util.MqUtil;
import com.together.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.locks.ReentrantLock;

@Service
public class SpellServiceImpl implements SpellService {


    @Autowired
    private MqUtil mqUtil;

    @Autowired
    private SetOperations setOperations;
    private ReentrantLock reentrantLock = new ReentrantLock(true);

    @Override
    public R startTogether(Integer userId, Integer goodsId, TogetherNumber togetherNumber) {
//        mqUtil.testSend();
        Spell spell = new Spell(userId, goodsId, togetherNumber);
        Long size = 1L;
        try {
            reentrantLock.lock();
            setOperations.add(SystemParameter.i.toString(), spell);
            size = setOperations.size(SystemParameter.i.toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            reentrantLock.unlock();
        }
        System.out.println("当前排队团号:" + SystemParameter.i.get() + "排队人数" + size);
            if (size > 99) {
                System.out.println("第" + SystemParameter.i.get() + "开拼");
                mqUtil.testSend("directExchange", "direct.key", SystemParameter.i.get());
                SystemParameter.i.addAndGet(1);
            }


        return null;
    }


    @Override
    public R startTogetherMq(int userId, int goodsId, TogetherNumber togetherNumber) {
        Spell spell = new Spell(userId, goodsId, togetherNumber);
        mqUtil.testSend(MqParameter.SPELL_EXCHANGE_NAME, MqParameter.SPELL_QUEUE_NAME, JSON.toJSONString(spell));
        return null;
    }
}
