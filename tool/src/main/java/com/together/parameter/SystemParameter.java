package com.together.parameter;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 系统配置参数类
 * @author  Agu
 */
public class SystemParameter {

    //初始化团号
    public  static AtomicInteger i = new AtomicInteger(1);

    //中奖人数
    public  static  final Integer  BINGO_NUMBER = 3;


}
