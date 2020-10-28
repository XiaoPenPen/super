package com.example.demo.kafka;

import cn.hutool.json.JSONUtil;
import com.example.demo.avro.DrillTaskConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author xuchunpeng 2020/10/28
 */
@Slf4j
@Component
public class KafkaListenes {

   // @KafkaListener(topics = "test_avro", containerFactory = "kafkaAvroContainerFactory")
    public void snowTest(DrillTaskConfig drillTaskConfig){
        System.out.println(JSONUtil.toJsonStr(drillTaskConfig));
    }

}
