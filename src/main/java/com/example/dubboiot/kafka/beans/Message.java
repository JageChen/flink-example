package com.example.dubboiot.kafka.beans;

import lombok.Data;

import java.util.Date;

/**
 * description: aaa <br>
 * date: 2020/1/3 11:28 <br>
 * author: EDZ <br>
 * version: 1.0 <br>
 */
@Data
public class Message {
    public long f1;
    private Long id;    //id

    private String msg; //消息

    private Date sendTime;  //时间戳
}
