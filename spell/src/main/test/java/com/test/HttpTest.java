package com.test;

import org.springframework.data.redis.connection.ReturnType;
import org.springframework.web.client.RestTemplate;
import redis.clients.jedis.Jedis;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class HttpTest {


    public static void delKey() throws Exception{
        Jedis jedis=new Jedis("47.114.38.198",6379);
        jedis.flushAll();
    }

    public static void main(String[] args) throws Exception{
        delKey();
        testThread();
    }

    public static void testThread(){
        RestTemplate restTemplate = new RestTemplate();
        CountDownLatch downLatch = new CountDownLatch(1);

        for (int i = 0; i <57 ; i++) {
            Thread thread = new Thread(){
                @Override
                public void run() {
                    try {
                        downLatch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Random random = new Random();
                    int i = random.nextInt(11111111);
//                    System.out.println(i);
                    restTemplate.getForObject("http://localhost:2001/spell/together?userId="+i+"&goodsId=1",Object.class);

                }
            };
            thread.start();
        }
        downLatch.countDown();
    }

}
