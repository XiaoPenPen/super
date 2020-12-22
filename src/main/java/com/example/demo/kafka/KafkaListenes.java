package com.example.demo.kafka;

import cn.hutool.json.JSONUtil;
import com.example.demo.avro.Feedback;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author xuchunpeng 2020/10/28
 */
@Slf4j
@Component
public class KafkaListenes {

    //@KafkaListener(topics = "topic_feedback_json1", containerFactory = "kafkaAvroContainerFactory")
    public void snowTest1(Feedback feedback){
        System.out.println(JSONUtil.toJsonStr(feedback));
    }

    //@KafkaListener(topics = "bdjc_config", containerFactory = "kafkaOneContainerFactory")
    public void snowTest2(Object value){
        System.out.println(value);
        System.out.println(JSONUtil.toJsonStr(value));
    }

}
