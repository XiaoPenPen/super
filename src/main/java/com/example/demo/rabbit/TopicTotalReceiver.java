package com.example.demo.rabbit;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;

import java.util.Map;

/**
 * @Author 主题消费者 全部
 **/

//@Component
//@RabbitListener(queues = "topic.woman")
public class TopicTotalReceiver {

    @RabbitHandler
    public void process(Map testMessage) {
        System.out.println("TopicTotalReceiver消费者收到消息  : " + testMessage.toString());
    }
}
