package com.example.demo.kafka;

import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONObject;
import com.example.demo.redis.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * @author xuchunpeng 2020/5/13
 */
@Component
@PropertySource({"classpath:properties/detection-task.properties"})
public class SnortTest11Producer {

    @Autowired
    private KafkaTemplate kafkaOneTemplate;
    @Autowired
    private KafkaTemplate kafkaTwoTemplate;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private Environment environment;
    @Value("${server.tomcat.uri-encoding}")
    private String value;
    public static List<String> userMap = Arrays.asList("xcp-test-o1", "wsd-test-01");
    public static List<String> targetNameMap = Arrays.asList("xcp-test-o1", "wsd-test-01");
    public static List<String> teamMap = Arrays.asList("xcp-test-o1", "wsd-test-01");

    @Value("${DetectionTask.applyTokenIn.port}")
    private Integer port;

    @Scheduled(cron = "*/2 * * * * ?")
    public void qadsadawa() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        System.out.println("进入时间"+DateUtil.now());
        String test = (String) redisUtil.leftPop("test_1");
        System.out.println(test+DateUtil.now());
    }

    //@Scheduled(cron = "*/5 * * * * ?")
    public void qweq() {
        ListenableFuture listenableFuture = kafkaOneTemplate.send("link_info", "{\"Status\": {\"Up\": \"G\",\"Down\": \"G\",\"Left\": \"G\",\"Right\":\"G\"}}");
        SendResult sendResult = checkProRecord(listenableFuture);
        System.out.println(sendResult);
    }
    //@Scheduled(cron = "*/10 * * * * ?")
    public void send() {
        Random random = new Random();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", UUID.randomUUID());
        jsonObject.put("userId","userId");
        jsonObject.put("targetName", "测试11");
        jsonObject.put("projectId","6fdb1ad085524822ac3528f55fb23d39");
        jsonObject.put("sceneId","d1bbd89859234fed8d52312e3f915940");

        jsonObject.put("createTime", DateUtil.now());
        jsonObject.put("isRight", 1);

        jsonObject.put("userName","xcp-test-01");

        jsonObject.put("teamId","70977cc0fefc4964b8bc2933426bed89");
        jsonObject.put("teamName","team1");

        jsonObject.put("deviceId","V8672a5449564deea58773886e1f9699");
        jsonObject.put("groupId", "TGb129d2-c4dc-4979-a214-d5f0e3a4dcc2");
        jsonObject.put("groupName", "公司总部");
        ListenableFuture listenableFuture = kafkaOneTemplate.send("USER_SUBMIT_TOPIC",jsonObject.toString());
        SendResult sendResult = checkProRecord(listenableFuture);
        System.out.println(sendResult);
    }

//    @Scheduled(cron = "*/30 * * * * ?")
//    public void send2() {
//        Random random = new Random();
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("id", UUID.randomUUID());
//        jsonObject.put("userId","userId");
//        jsonObject.put("targetName", "测试22");
//        jsonObject.put("projectId","6fdb1ad085524822ac3528f55fb23d39");
//        jsonObject.put("sceneId","d1bbd89859234fed8d52312e3f915940");
//        jsonObject.put("createTime",DateUtil.now());
//        jsonObject.put("isRight", 1);
//
//        jsonObject.put("userName","wsd-test-01");
//
//        jsonObject.put("teamId","6fa814e2e82547a38f65f66d85f62d0c");
//        jsonObject.put("teamName","team7");
//
//        jsonObject.put("deviceId","Vb08df55623144bb87a32eb7f5961c31");
//        jsonObject.put("groupId", "TG835eb8-9b91-4d0c-9c24-fb24dd0834cc");
//        jsonObject.put("groupName", "生产分公司");
//        ListenableFuture listenableFuture = kafkaTemplate.send("USER_SUBMIT_TOPIC",jsonObject.toString());
//        SendResult sendResult = checkProRecord(listenableFuture);
//        System.out.println(sendResult);
//    }
//
//    @Scheduled(cron = "*/40 * * * * ?")
//    public void send3() {
//        Random random = new Random();
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("id", UUID.randomUUID());
//        jsonObject.put("userId","userId");
//        jsonObject.put("targetName", "测试33");
//        jsonObject.put("projectId","6fdb1ad085524822ac3528f55fb23d39");
//        jsonObject.put("sceneId","d1bbd89859234fed8d52312e3f915940");
//        jsonObject.put("createTime",DateUtil.now());
//        jsonObject.put("isRight", 1);
//
//        jsonObject.put("userName","wsd-test-02");
//
//        jsonObject.put("teamId","c16c6a891eca4e168ba729abbedd9e3b");
//        jsonObject.put("teamName","team5");
//
//        jsonObject.put("deviceId","Vcd3be8ef34f4ee3bc6c7f0231d45316");
//        jsonObject.put("groupId", "TG3466a9-e526-4613-bb2f-e2c17de8e7bd");
//        jsonObject.put("groupName", "研发中心");
//        ListenableFuture listenableFuture = kafkaTemplate.send("USER_SUBMIT_TOPIC",jsonObject.toString());
//        SendResult sendResult = checkProRecord(listenableFuture);
//        System.out.println(sendResult);
//    }
//
//    @Scheduled(cron = "*/20 * * * * ?")
//    public void send4() {
//        Random random = new Random();
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("id", UUID.randomUUID());
//        jsonObject.put("userId","userId");
//        jsonObject.put("targetName", "测试44");
//        jsonObject.put("projectId","6fdb1ad085524822ac3528f55fb23d39");
//        jsonObject.put("sceneId","d1bbd89859234fed8d52312e3f915940");
//        jsonObject.put("createTime",DateUtil.now());
//        jsonObject.put("isRight", 1);
//
//        jsonObject.put("userName","wsd-test-03");
//
//        jsonObject.put("teamId","aa11b9bae89a4aeaa51029fd8ce3d6a2");
//        jsonObject.put("teamName","team8");
//
//        jsonObject.put("deviceId","V7b3158eefe4483bbec703baba57d511");
//        jsonObject.put("groupId", "TG41d116-26d1-4471-be5f-fcc19fce9a83");
//        jsonObject.put("groupName", "自来水厂");
//        ListenableFuture listenableFuture = kafkaTemplate.send("USER_SUBMIT_TOPIC",jsonObject.toString());
//        SendResult sendResult = checkProRecord(listenableFuture);
//        System.out.println(sendResult);
//    }
//
//    @Scheduled(cron = "*/8 * * * * ?")
//    public void send5() {
//        Random random = new Random();
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("id", UUID.randomUUID());
//        jsonObject.put("userId","userId");
//        jsonObject.put("targetName", "测试55");
//        jsonObject.put("projectId","6fdb1ad085524822ac3528f55fb23d39");
//        jsonObject.put("sceneId","d1bbd89859234fed8d52312e3f915940");
//        jsonObject.put("createTime",DateUtil.now());
//        jsonObject.put("isRight", 1);
//
//        jsonObject.put("userName","wsd-test-04");
//
//        jsonObject.put("teamId","84ad21130f8a448bb3e98a883e0a35ae");
//        jsonObject.put("teamName","team2");
//
//        jsonObject.put("deviceId","V041bea64c0a44bfbd1c3178364a5472");
//        jsonObject.put("groupId", "TG0a7dba-fba3-48ba-98d9-064484d38872");
//        jsonObject.put("groupName", "交通指挥中心");
//        ListenableFuture listenableFuture = kafkaTemplate.send("USER_SUBMIT_TOPIC",jsonObject.toString());
//        SendResult sendResult = checkProRecord(listenableFuture);
//        System.out.println(sendResult);
//    }

    //@Scheduled(cron = "*/2 * * * * ?")
//    public void send2() {
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("userName","testName1");
//        jsonObject.put("project_id","d44ef000488545e387e927491cb2e6fc");
//        ListenableFuture listenableFuture = kafkaTemplate.send("USER_SUBMIT_TOPIC",jsonObject.toString());
//        SendResult sendResult = checkProRecord(listenableFuture);
//        System.out.println(sendResult);
//    }


//    @Scheduled(cron = "*/3 * * * * ?")
//    public void send() {
//        // DateUtil.current(false)
//        String value = "{\n" +
//                "        \"reason\": \"attack reason\",\n" +
//                "        \"time\": "+ DateUtil.current(false) +",\n" +
//                "        \"alert_info\": {\n" +
//                "            \"source\": {\n" +
//                "                \"ip_addr\": \"192.168.12.56\",\n" +
//                "                \"mac\": \"11:22:33:44:55:66\",\n" +
//                "                \"instance_name\": \"instance-123456\",\n" +
//                "                \"protocal\": 4,\n" +
//                "                \"project_id\": \"uuid_string\"\n" +
//                "            },\n" +
//                "            \"destination\": {\n" +
//                "                \"ip\": \"192.168.12.34\",\n" +
//                "                \"mac\": \"aa:bb:cc:dd:ee:ff\",\n" +
//                "                \"protocal\": 4,\n" +
//                "                \"instance_name\": \"instance-abcdef\",\n" +
//                "                \"project_id\": \"uuid_string\"\n" +
//                "            }\n" +
//                "        }\n" +
//                "    }";
//        ListenableFuture listenableFuture = kafkaTemplate.send("snort_test11",value);
//        SendResult sendResult = checkProRecord(listenableFuture);
//        System.out.println(DateUtil.now());
//    }

   // @Scheduled(cron = "*/1 * * * * ?")
    public void test() {
        /*// DateUtil.current(false)
        String value = "{\n" +
                "        \"reason\": \"attack reason\",\n" +
                "        \"time\": "+ DateUtil.current(false) +",\n" +
                "        \"alert_info\": {\n" +
                "            \"source\": {\n" +
                "                \"ip_addr\": \"192.168.12.56\",\n" +
                "                \"mac\": \"11:22:33:44:55:66\",\n" +
                "                \"instance_name\": \"instance-123456\",\n" +
                "                \"protocal\": 4,\n" +
                "                \"project_id\": \"uuid_string\"\n" +
                "            },\n" +
                "            \"destination\": {\n" +
                "                \"ip\": \"192.168.12.34\",\n" +
                "                \"mac\": \"aa:bb:cc:dd:ee:ff\",\n" +
                "                \"protocal\": 4,\n" +
                "                \"instance_name\": \"instance-abcdef\",\n" +
                "                \"project_id\": \"uuid_string\"\n" +
                "            }\n" +
                "        }\n" +
                "    }";
        ListenableFuture listenableFuture = kafkaTemplate.send("snort_test11",value);*/
    }

    /**
     * 检查发送返回结果record
     *
     * @param res
     * @return
     */
    public SendResult checkProRecord(ListenableFuture<SendResult<String, String>> res) {
        try {
            //检查result结果集
            SendResult r = res.get();
            /*检查recordMetadata的offset数据，不检查producerRecord*/
            Long offsetIndex = r.getRecordMetadata().offset();
            String topic = r.getRecordMetadata().topic();
            Object value = r.getProducerRecord().value();
            return r;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
