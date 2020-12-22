package com.example.demo.avro;

import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.example.demo.common.Result;
import com.google.common.collect.ImmutableMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;

/**
 * @author xuchunpeng 2020/10/27
 */
@Slf4j
@RestController
@RequestMapping("avro")
public class UserController {

    @Resource(name = "avroKafkaTemplate")
    private KafkaTemplate<String, Feedback> kafkaTemplate;

    @Resource
    private KafkaTemplate kafkaOneTemplate;

    @PostMapping("feedback")
    public Result feedback(@RequestParam String category, @RequestBody JSONObject jsonObject){
        Feedback feedback = new Feedback();
        feedback.setProjectId("6fdb1ad085524822ac3528f55fb23d39");
        feedback.setDataCategory(category);
        feedback.setReturnTime(DateUtil.currentSeconds());
        feedback.setTaskId("6fdb1ad085524822ac3528f55fb23d39");
        feedback.setScenarioId("6fdb1ad085524822ac3528f55fb23d39");
        feedback.setReturnValue(JSONUtil.toJsonStr(jsonObject));
        kafkaTemplate.send("topic_feedback_json1", feedback);
        return Result.init(JSONUtil.toJsonStr(jsonObject));
    }

    @PostMapping("attack")
    public Result attack(@RequestBody JSONObject jsonObject){
        kafkaOneTemplate.send("attack_syslog", JSONUtil.toJsonStr(jsonObject));
        return Result.init(JSONUtil.toJsonStr(jsonObject));
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
        feedback.setProjectId("82b7e280b01f40f584dd1a82dc3e392f");
        feedback.setReturnTime(DateUtil.currentSeconds());
        feedback.setReturnValue("{" +
                "\"taskid\":\"82b7e280b01f40f584dd1a82dc3e392f\", " +
                "\"content_type\":\"abstract\"," +
                "\"log_time\": \"2020-10-29 13:50:00\"," +
                "    \"event_num\":[ " +
                "        {" +
                "            \"event_time\":\"2020-10-29 13:50:00\"," +
                "            \" event _num\":123 " +
                "        }," +
                "        {" +
                "            \"event_time\":\"2020-10-29 13:50:00\"," +
                "            \" event _num\":123 " +
                "        }" +
                "    ]," +
                "    \"attacker\":[" +
                "        {" +
                "            \"ipmac\":\"172.16.18.1&00-01-6C-06-A6-29\", " +
                "            \"nums\":123" +
                "        }," +
                "        {" +
                "            \"ipmac\":\"172.16.18.1&00-01-6C-06-A6-29\"," +
                "            \"nums\":123" +
                "        }" +
                "    ]," +
                "    \"victim\":[" +
                "        {" +
                "            \"ipmac\":\"172.16.18.1&00-01-6C-06-A6-29\"," +
                "            \"nums\":123" +
                "        }," +
                "        {" +
                "            \"ipmac\":\"172.16.18.1&00-01-6C-06-A6-29\"," +
                "            \"nums\":123" +
                "        }" +
                "]," +
                "\"event_type_num\":[" +
                "        {" +
                "            \"event_type\":\"1\"," +
                "            \"nums\":123" +
                "        }," +
                "        {" +
                "            \"event_type\":\"2\"," +
                "            \"nums\":123" +
                "        }," +
                "        {" +
                "            \"event_type\":\"3\"," +
                "            \"nums\":123" +
                "        }," +
                "        {" +
                "            \"event_type\":\"4\"," +
                "            \"nums\":123" +
                "        }," +
                "        {" +
                "            \"event_type\":\"5\"," +
                "            \"nums\":123" +
                "        }" +
                "    ]" +
                "}");
        feedback.setScenarioId("ssssssss");
        feedback.setTaskId("tttttttt");
        kafkaTemplate.send("test_avro", feedback);
        return Result.init();
    }

}
