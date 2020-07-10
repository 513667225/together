package com.together.aspect;

import com.together.annotation.Login;
import com.together.enun.TipMsgEnum;
import com.together.local.RequestThreadLocal;
import com.together.parameter.RedisParamenter;
import com.together.util.R;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * @author Agu
 */
@Aspect
@Component
public class LoginAspect {


    @Autowired
    ValueOperations operations;

    @Around("@annotation(com.together.annotation.Login)")
    public Object loginAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        //拿到类对象
        Class<?> aClass = proceedingJoinPoint.getTarget().getClass();
        //拿到方法签名
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
//        signature.get
        //拿到类上面加的Login注解
        Login classAnnotation = aClass.getAnnotation(Login.class);
        //拿到方法对象
        Method method = signature.getMethod();
//        Method method = signature.getMethod();
        //拿到方法上面加的Login注解
        Login methodAnnotation = method.getAnnotation(Login.class);
        //定义登陆用户对象
        Object user = null;
        //如果类上面加了Login注解或者方法上面加了login注解或者前两者都加了而且并没有跳过的话就会判断用户是否登陆

        // 从本地线程副本里面取出Request对象
        HttpServletRequest httpServletRequest = RequestThreadLocal.REQUEST_THREAD_LOCAL.get();
        String adminUserKey="";
        String shopUserKey="";
        for (Cookie cookie : httpServletRequest.getCookies()) {
            if (RedisParamenter.ADMIN_LOING_USER_REDIS_KEY.equals(cookie.getName())){
                adminUserKey = cookie.getValue();
            }
            if (RedisParamenter.SHOP_LOING_USER_REDIS_KEY.equals(cookie.getName())){
                shopUserKey = cookie.getValue();
            }

        }
        if (classAnnotation != null || methodAnnotation != null || !methodAnnotation.skip()) {
            //如果方法上有注解isAdmin就根据方法上来，如果方法上没注解就根据类来
            boolean isAdmin = methodAnnotation==null? classAnnotation.isAdmin() : methodAnnotation.isAdmin();
            if (isAdmin) {
                //去redis里面判断系统用户是否登录
                user = operations.get(adminUserKey);
            } else {
                //去redis判断商家用户是否登陆
                user = operations.get(shopUserKey);
            }
            if (user ==null){
                return R.error(isAdmin? TipMsgEnum.ADMIN_USER_NOT_LOGIN:TipMsgEnum.SHOP_USER_NOT_LOGIN);
            }
        }
        return proceedingJoinPoint.proceed(proceedingJoinPoint.getArgs());

    }

}
