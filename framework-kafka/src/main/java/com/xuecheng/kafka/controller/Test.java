package com.xuecheng.kafka.controller;

import com.xuecheng.kafka.component.KafkaSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
public class Test {

    @Autowired
    private KafkaSender kafkaSender;

    @RequestMapping("/test")
    public String test(){
        for (int i = 0; i < 3; i++) {
            //调用消息发送类中的消息发送方法
            kafkaSender.send();
        }

        Integer s = 5;
        return "sdfsdf";
    }
}
