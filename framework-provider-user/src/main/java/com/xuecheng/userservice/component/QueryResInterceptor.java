package com.xuecheng.userservice.component;

import org.hibernate.EmptyInterceptor;
import org.hibernate.Transaction;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueryResInterceptor extends EmptyInterceptor {
    @Override
    public void beforeTransactionCompletion(Transaction tx) {
        super.beforeTransactionCompletion(tx);
    }

    @Override
    public String onPrepareStatement(String sql) {
        System.out.println("打印sql语句:"+sql);
        return sql;
    }
}
