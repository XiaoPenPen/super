package com.example.demo.kafka;


import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;

import java.util.Collections;
import java.util.Properties;

/**
 * @author xuchunpeng 2020/5/13
 */
@Slf4j
public class SnortTest11Consumer {
    private static final String KAFKA_BOOTSTRAP_SERVERS = "bootstrap.servers";
    private static final String KAFKA_CONSUMER_GROUP_ID = "group.id";
    private static final String KAFKA_CONSUMER_AUTO_COMMIT = "enable.auto.commit";
    private static final String KAFKA_KEY_DESERIALIZER ="key.deserializer";
    private static final String KAFKA_VALUE_DESERIALIZER ="value.deserializer";
    private static final String KAFKA_CONSUMER_MAX_POLL_RECORDS ="max.poll.records";

    /**
     * 初始化kafka consumer
     */
    public static KafkaConsumer initKafkaConsumer(String broker, String group, int maxPollRecords) {
        Properties props = new Properties();

        props.put(KAFKA_BOOTSTRAP_SERVERS, broker);
        props.put(KAFKA_CONSUMER_GROUP_ID, group);
        props.put(KAFKA_CONSUMER_AUTO_COMMIT, "false");
        props.put(KAFKA_CONSUMER_MAX_POLL_RECORDS, maxPollRecords);
        props.put(KAFKA_KEY_DESERIALIZER, "org.apache.kafka.common.serialization.StringDeserializer");
        props.put(KAFKA_VALUE_DESERIALIZER,"org.apache.kafka.common.serialization.StringDeserializer");
        log.info("properties={}", props.toString());

        return new KafkaConsumer(props);
    }

    public static void main(String[] args) {
        String broker = "10.0.0.12:9092,10.0.0.13:9092,10.0.0.16:9092";
        String group = "test-demo";
        int partition = 1;
        String topic = "topic_feedback";
        long offset = 18769L;
        int maxPollRecords = 20;

        log.info("broker={}, group={}, partition={}, topic={}, offset={}", broker, group, partition, topic, offset);

        KafkaConsumer consumer = initKafkaConsumer(broker, group, maxPollRecords);
        TopicPartition targetPartition = new TopicPartition(topic, partition);
        // 指定消费分区，如果去掉该行，则不能消费到数据
        consumer.assign(Collections.singletonList(targetPartition));
        // poll一次数据，分配partition
        consumer.poll(100);
        // 指定消费起始offset
        consumer.seek(targetPartition, offset);
        // 真正拉取目标数据
        ConsumerRecords<String, String> records = consumer.poll(100);
        for (ConsumerRecord<String, String> record : records) {
            // 数据处理，这里我简单用日志输出
            log.info(String.valueOf(record));
        }

        try {
            consumer.close();
            log.info("kafka consumer has been closed, previous topic name ={}", topic);
        } catch (Exception e) {
            log.error("an exception happened while close kafka consumer:", e);
        }

    }
}
