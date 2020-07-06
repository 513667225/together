package com.together;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
public class AppOrder {

    public static void main(String[] args) {
        SpringApplication.run(AppOrder.class);
    }
}
