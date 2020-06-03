package com.example.demo.kafka;


import cn.hutool.core.date.DateUtil;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

/**
 * @author xuchunpeng 2020/5/13
 */
public class SnortTest11Consumer {
    public static void main(String[] args) {
        Properties p = new Properties();
        List<String> reslutList = new ArrayList<>();
        p.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "172.18.0.46:9092,172.18.0.47:9092,172.18.0.48:9092");
        p.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        p.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        p.put(ConsumerConfig.GROUP_ID_CONFIG, "screen-control-demo");
        p.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<>(p);
        // 订阅消息
        kafkaConsumer.subscribe(Collections.singletonList("snort_test11"));
        ConsumerRecords<String, String> records = kafkaConsumer.poll(Duration.ofMillis(10000));// 超时时间 2s
        for (ConsumerRecord<String, String> record : records) {
            System.out.println(record.offset() + "___" + DateUtil.date(record.timestamp()));
        }
        kafkaConsumer.commitSync();
    }
}
