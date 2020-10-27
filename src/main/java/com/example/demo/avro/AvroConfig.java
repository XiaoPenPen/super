package com.example.demo.avro;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xcp
 * @creat 2020-03-11-20:23
 */
@Configuration
@EnableKafka
public class AvroConfig {

    @Value("${spring.kafka.one.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${spring.kafka.one.producer.max-request-size}")
    private String maxRequestSize;

    @Bean
    public Map<String, Object> avroProducerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ProducerConfig.MAX_REQUEST_SIZE_CONFIG, maxRequestSize);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, AvroSerializer.class);
        return props;
    }

    @Bean
    public ProducerFactory<String, User> elProducerFactory() {
        return new DefaultKafkaProducerFactory<>(avroProducerConfigs());
    }

    @Bean(name = "avroKafkaTemplate")
    public KafkaTemplate<String, User> elKafkaTemplate() {
        return new KafkaTemplate<>(elProducerFactory());
    }
}