package com.together.config;


import com.together.resolvers.PmapResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MvcConfig {



    @Bean
    public PmapResolver resolver(){

        return  new PmapResolver();
    }




}
