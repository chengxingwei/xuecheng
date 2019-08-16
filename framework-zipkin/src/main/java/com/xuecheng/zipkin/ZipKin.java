package com.xuecheng.zipkin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import zipkin2.server.internal.EnableZipkinServer;
import javax.sql.DataSource;

@SpringBootApplication
@EnableZipkinServer
public class ZipKin {
    public static void main(String[] args){
        SpringApplication.run(ZipKin.class,args);
    }
}
