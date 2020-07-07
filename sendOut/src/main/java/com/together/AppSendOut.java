package com.together;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author Agu
 */
@SpringBootApplication
@EnableFeignClients
public class AppSendOut {

    public static void main(String[] args) {
        SpringApplication.run(AppSendOut.class);
    }
}
