package com.example.demo.kafka;

import cn.hutool.core.io.FileUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.example.demo.avro.Feedback;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.File;

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
    public static int i =0;
    //@KafkaListener(topics = "bdjc_config", containerFactory = "kafkaOneContainerFactory")
    public void snowTest2(Object value){
        System.out.println(value);
        System.out.println(JSONUtil.toJsonStr(value));
    }

    public static void main(String[] args) {
        String data = FileUtil.readString(new File("C:\\Users\\15711\\Desktop\\ip对应虚拟机.json"), "UTF-8");

        JSONUtil.parseObj(data).getJSONArray("RECORDS").forEach(str -> {
            JSONObject jsonObject = JSONUtil.parseObj(str);
            System.out.println("redisUtil.hset(WebSocketConstant.IP_NAME_CONVERT,\""+ jsonObject.get("ip") +"\",\""+ jsonObject.get("name") +"\");");
        });


    }

}
