package com.xuecheng.kafka.component;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Message implements Serializable {
    private Long id;    //id

    private String msg; //消息

    private Date sendTime;  //时间戳
}
