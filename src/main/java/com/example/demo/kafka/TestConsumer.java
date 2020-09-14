package com.example.demo.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author xuchunpeng 2020/5/13
 */
@SuppressWarnings("Duplicates")
@Slf4j
@Component
public class TestConsumer {

    /**
     * 测试twokafka
     * @param record
     */
    //@KafkaListener(topics = "test", containerFactory = "kafkaTwoContainerFactory")
    public void closeWebSocketConnect(ConsumerRecord<String, String> record){
        Optional<String> value = Optional.ofNullable(record.value());
        if(value.isPresent()){
            System.out.println("kafka two :" + value);
        }
    }


}

