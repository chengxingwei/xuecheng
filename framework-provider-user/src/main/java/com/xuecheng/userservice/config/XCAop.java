package com.xuecheng.userservice.config;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

@Aspect
@Configuration
public class XCAop {

    @Autowired
    private EntityManager entityManager;

    /*
     * 定义一个切入点
     */
    // @Pointcut("execution (* findById*(..))")
    @Pointcut("execution(public * com.xuecheng.userservice.service.*.*(..))")
    public void excudeService(){

    }
    /*
     * 通过连接点切入
     */
    @Before("excudeService()")
    public void twiceAsOld1(JoinPoint joinPoint) throws Throwable{
//        System.out.println("1");
//        Filter filter = (Filter)entityManager.unwrap(Session.class).enableFilter("employeeFilter");
//        filter.setParameter("emplNumber", "aa@atguigu.com");
//        System.out.println("2");
    }


    @AfterReturning("excudeService()")
    public void absdf(JoinPoint joinPoint) throws Throwable{
//        System.out.println("3");
//        entityManager.unwrap(Session.class).disableFilter("employeeFilter");
//        System.out.println("4");
    }



}
