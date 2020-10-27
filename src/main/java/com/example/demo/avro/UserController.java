package com.example.demo.avro;

import com.example.demo.common.Result;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author xuchunpeng 2020/10/27
 */
@RestController
@RequestMapping("avro")
public class UserController {

    @Resource(name = "avroKafkaTemplate")
    private KafkaTemplate<String, User> kafkaTemplate;

    @RequestMapping("test")
    public Result test(){
        kafkaTemplate.send("123", new User());
        return Result.init();
    }
}
