package com.example.demo.avro;

import cn.hutool.core.date.DateUtil;
import com.example.demo.common.Result;
import com.google.common.collect.ImmutableMap;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Arrays;

/**
 * @author xuchunpeng 2020/10/27
 */
@RestController
@RequestMapping("avro")
public class UserController {

    @Resource(name = "avroKafkaTemplate")
    private KafkaTemplate<String, Feedback> kafkaTemplate;

    @GetMapping("test")
    public Result test(){
/*        User user = new User();
        user.setAge(22);
        user.setName("XCP2");
        user.setAddress("海淀");
        kafkaTemplate.send("test_avro", user);*/
        return Result.init();
    }

    @GetMapping("test2")
    public Result test2(@RequestParam String projectId){
        DrillTaskConfig drillTaskConfig = new DrillTaskConfig();
        drillTaskConfig.setTaskId(projectId);
        drillTaskConfig.setProjectId(projectId);
        drillTaskConfig.setScenarioId("992b94e6ca644ea485238a35c9ef7af2");
        drillTaskConfig.setDate(DateUtil.now());
        drillTaskConfig.setUserID("fdasfsdafdsaf");
        drillTaskConfig.setLinks(Arrays.asList("vm1", "vm2", "vm3"));
        Device device = new Device();
        device.setVmId("V1c022aaaa514820afaf666c463f364c");
        device.setName("WIN7");
        device.setAddress(ImmutableMap.of("mac","fa:16:3e:12:c5:7f", "ip", "172.1.0.101"));
        Device device2 = new Device();
        device2.setVmId("V6a1e99874fc48bdb88e4a8afb4dbd10");
        device2.setName("WIN10");
        device2.setAddress(ImmutableMap.of("mac","fa:16:3e:26:44:c7", "ip", "172.1.0.103"));
        drillTaskConfig.setDevices(Arrays.asList(device, device2));

       // kafkaTemplate.send("test_avro", drillTaskConfig);
        return Result.init();
    }

    @GetMapping("test3")
    public Result test3(){
        Feedback feedback = new Feedback();
        feedback.setProjectId("qqqqqq");
        feedback.setReturnTime(1231231231L);
        feedback.setReturnValue("这是一条测试");
        feedback.setScenarioId("ssssssss");
        feedback.setSystemName("ZHFX");
        feedback.setTaskId("tttttttt");
        kafkaTemplate.send("test_avro", feedback);
        return Result.init();
    }

}
