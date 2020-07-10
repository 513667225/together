package com.together.local;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Agu
 */
public class RequestThreadLocal {

    //本地线程存储对象
    public  static  final  ThreadLocal<HttpServletRequest> REQUEST_THREAD_LOCAL = new ThreadLocal();

}
