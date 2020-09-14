package com.example.demo.rabbit;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;

import java.util.Map;

//@Component
//@RabbitListener(queues = "fanout.B")
public class FanoutReceiverB {

    @RabbitHandler
    public void process(Map testMessage) {
        System.out.println("FanoutReceiverB消费者收到消息  : " +testMessage.toString());
    }

}
