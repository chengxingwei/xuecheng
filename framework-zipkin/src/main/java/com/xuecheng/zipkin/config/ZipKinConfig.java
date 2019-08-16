package com.xuecheng.zipkin.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import zipkin.storage.mysql.MySQLStorage;

import javax.sql.DataSource;

@Configuration
public class ZipKinConfig {

    @Autowired
    private DataSource dataSource;

    @Bean
    public MySQLStorage mySQLStorage() {
        return MySQLStorage.builder().datasource(dataSource).executor(Runnable::run).build();
    }
}
