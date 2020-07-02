package com.together.modules.user.utli;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Scope("singleton")
public class RedisIdUtil {


    @Autowired
    private ValueOperations valueOperations;

    // static 属性变量
    private static ValueOperations stringTemplate;

    @PostConstruct
    public void init() {
        // 临时的bean引用 赋给 static 属性
        RedisIdUtil.stringTemplate = valueOperations;
    }

    public static Integer nextId(String idName){
        return stringTemplate.increment(idName).intValue();
    }


}