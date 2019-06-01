package com.xuecheng.userservice.controller;



import com.xuecheng.userservice.cloudstream.StreamClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Component
@EnableBinding(value = {StreamClient.class})
public class StreamReceiver {


    @StreamListener(StreamClient.INPUT)
    public void receive(String message){
        System.out.println("收到消息："+message);
    }
}
