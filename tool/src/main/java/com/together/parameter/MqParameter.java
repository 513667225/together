package com.together.parameter;

/**
 * Mq配置相关参数
 * @author  Agu
 */
public class MqParameter {

    public static final String SPELL_QUEUE_NAME = "spellQueue";
    public static final String WAIT_QUEUE_NAME = "waitQueue";
    public static final String LOSER_QUEUE_NAME = "loserQueues";
    public static final String CREATE_GROUP_QUEUE_NAME = "createGroupQueue";


    public static final  String SPELL_EXCHANGE_KEY_NAME = "direct.key";
    public static final  String WAIT_EXCHANGE_KEY_NAME = "direct.key";
    public static final String LOSER_EXCHANGE_KEY_NAME = "direct.key";
    public static final String CREATE_GROUP_EXCHANGE_KEY_NAME = "direct.key";



    public static final  String SPELL_EXCHANGE_NAME = "SpellDirectExchange";
    public static final  String WAIT_EXCHANGE_NAME = "WaitDirectExchange";
    public static final String LOSER_EXCHANGE_NAME = "LoserDirectExchange";
    public static final String CREATE_GROUP_EXCHANGE_NAME = "createGroupExchange";
}
