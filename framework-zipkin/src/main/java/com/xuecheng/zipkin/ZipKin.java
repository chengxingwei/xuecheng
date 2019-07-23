package com.xuecheng.zipkin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import zipkin2.server.internal.EnableZipkinServer;

@SpringBootApplication
@EnableZipkinServer
public class ZipKin {
    public static void main(String[] args){
        SpringApplication.run(ZipKin.class,args);
    }
}
