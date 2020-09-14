package com.example.demo.rabbit;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;

import java.util.Map;

/** 主题消费者 男
 * @author admin
 */
//@Component
//@RabbitListener(queues = "topic.man")
public class TopicManReceiver {

    @RabbitHandler
    public void process(Map testMessage) {
        System.out.println("TopicManReceiver消费者收到消息  : " + testMessage.toString());
    }
}
