package com.example.demo.rabbit;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;

import java.util.Map;

//@Component
//@RabbitListener(queues = "fanout.A")
public class FanoutReceiverA {

    @RabbitHandler
    public void process(Map testMessage) {
        System.out.println("FanoutReceiverA消费者收到消息  : " +testMessage.toString());
    }

}
