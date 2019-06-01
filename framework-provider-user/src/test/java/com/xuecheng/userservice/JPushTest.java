package com.xuecheng.userservice;

import cn.jiguang.common.TimeUnit;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jiguang.common.resp.DefaultResult;
import cn.jpush.api.JPushClient;
import cn.jpush.api.device.OnlineStatus;
import cn.jpush.api.device.TagAliasResult;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;
import cn.jpush.api.report.MessageStatus;
import cn.jpush.api.report.MessagesResult;
import cn.jpush.api.report.ReceivedsResult;
import cn.jpush.api.report.UsersResult;
import cn.jpush.api.report.model.CheckMessagePayload;
import org.junit.Test;

import java.util.*;

public class JPushTest {
    @Test
    public void jpushTest1(){
        //推送测试
        JPushClient jpushClient = new JPushClient("3410d601e1e9bb687ecbb03e","f7d2b3d2ad3691fff770cbef");
        try {
            PushResult result = jpushClient.sendPush(PushPayload.newBuilder()
                    .setPlatform(Platform.all())
                    .setAudience(Audience.all())
                    .setNotification(Notification.alert("api测试"))
                    .build());
            System.out.println("Jpush:result"+result);
        } catch (APIConnectionException e) {
            // Connection error, should retry later
        } catch (APIRequestException e) {

        }
    }



    @Test
    public void jpushTest02(){//688758261   送达统计
        JPushClient jpushClient = new JPushClient("3410d601e1e9bb687ecbb03e","f7d2b3d2ad3691fff770cbef");
        try {
            ReceivedsResult result = jpushClient.getReportReceiveds("688758261,688758261");
            System.out.println("Jpush result:"+result);
        } catch (APIConnectionException e) {
            // Connection error, should retry later
        } catch (APIRequestException e) {

        }
    }

    @Test
    public void jpushTest03() {//688758261   送达状态查询
        JPushClient jpushClient = new JPushClient("3410d601e1e9bb687ecbb03e","f7d2b3d2ad3691fff770cbef");
        try {
            List list = new ArrayList();
            list.add("120c83f760794cbc02b");
            Map<String, MessageStatus> map = jpushClient.getMessageStatus(new CheckMessagePayload(688758261L,list,"2019-02-25"));
            System.out.println("Jpush result:"+map);
        } catch (APIConnectionException e) {
            // Connection error, should retry later
        } catch (APIRequestException e) {

        }
    }

    @Test
    public void jpushTest04() {//688758261   消息统计
        JPushClient jpushClient = new JPushClient("3410d601e1e9bb687ecbb03e","f7d2b3d2ad3691fff770cbef");
        try {

            MessagesResult result =jpushClient.getReportMessages("688758261");

            System.out.println("Jpush result:"+result);
        } catch (APIConnectionException e) {
            // Connection error, should retry later
        } catch (APIRequestException e) {

        }
    }

    @Test
    public void jpushTest05() {//688758261   用户统计
        JPushClient jpushClient = new JPushClient("3410d601e1e9bb687ecbb03e","f7d2b3d2ad3691fff770cbef");
        try {
            UsersResult result =jpushClient.getReportUsers(TimeUnit.DAY,"2014-06-11",20);

            System.out.println("Jpush result:"+result);
        } catch (APIConnectionException e) {
            // Connection error, should retry later
        } catch (APIRequestException e) {

        }
    }


    @Test//查询设备别名和标签
    public void jpushTest06() {//查询设备别名和标签   //reg_id    120c83f760794cbc02b
        JPushClient jpushClient = new JPushClient("3410d601e1e9bb687ecbb03e","f7d2b3d2ad3691fff770cbef");
        try {
            TagAliasResult result =jpushClient.getDeviceTagAlias("120c83f760794cbc02b");

            System.out.println("Jpush result:"+result);
        } catch (APIConnectionException e) {
            // Connection error, should retry later
        } catch (APIRequestException e) {

        }
    }

    @Test//清空设备别名和标签
    public void jpushTest07() {   //reg_id    120c83f760794cbc02b
        JPushClient jpushClient = new JPushClient("3410d601e1e9bb687ecbb03e","f7d2b3d2ad3691fff770cbef");
        try {
            DefaultResult result =jpushClient.updateDeviceTagAlias("120c83f760794cbc02b",true,true);

            System.out.println("Jpush result:"+result);
        } catch (APIConnectionException e) {
            // Connection error, should retry later
        } catch (APIRequestException e) {

        }
    }

    @Test//设置设备别名和标签
    public void jpushTest08() {   //reg_id    120c83f760794cbc02b
        JPushClient jpushClient = new JPushClient("3410d601e1e9bb687ecbb03e","f7d2b3d2ad3691fff770cbef");
        try {

            Set<String> toadd  = new HashSet<>();
            Set<String> toremove = new HashSet<>();
            toadd.add("cxw");
            DefaultResult result =jpushClient.updateDeviceTagAlias("120c83f760794cbc02b","cxw",toadd,toremove);
            System.out.println("Jpush result:"+result);
        } catch (APIConnectionException e) {
            // Connection error, should retry later
        } catch (APIRequestException e) {

        }
    }


    @Test//获取用户在线状态
    public void jpushTest09() {   //reg_id    120c83f760794cbc02b
        JPushClient jpushClient = new JPushClient("3410d601e1e9bb687ecbb03e","f7d2b3d2ad3691fff770cbef");
        try {
            Map<String, OnlineStatus> result =jpushClient.getUserOnlineStatus("120c83f760794cbc02b");
            System.out.println("Jpush result:"+result);
        } catch (APIConnectionException e) {
            // Connection error, should retry later
        } catch (APIRequestException e) {

        }
    }


    @Test//设备绑定手机号
    public void jpushTest10() {   //reg_id    120c83f760794cbc02b
        JPushClient jpushClient = new JPushClient("3410d601e1e9bb687ecbb03e","f7d2b3d2ad3691fff770cbef");
        try {
            DefaultResult result =jpushClient.bindMobile("120c83f760794cbc02b","");
            System.out.println("Jpush result:"+result);
        } catch (APIConnectionException e) {
            // Connection error, should retry later
        } catch (APIRequestException e) {

        }
    }
}
