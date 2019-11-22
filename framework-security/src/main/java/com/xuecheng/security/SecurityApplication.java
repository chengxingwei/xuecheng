package com.xuecheng.security;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

@SpringBootApplication
@EnableFeignClients(basePackages= {"com.xuecheng"})
@ComponentScan("com.xuecheng")
@EnableDiscoveryClient //服务发现
@EnableCircuitBreaker//对hystrixR熔断机制的支持
@EntityScan("com.xuecheng.entity")
@MapperScan("com.xuecheng.security.mapper")
@EnableAuthorizationServer
//@EnableWebSecurity
public class SecurityApplication {
    public static void main(String[] args){
        SpringApplication.run(SecurityApplication.class,args);
    }
}