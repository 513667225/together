package com.together;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AppReMoney {


    public static void main(String[] args) {
        SpringApplication.run(AppReMoney.class);
    }
}
