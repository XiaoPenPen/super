package com.example.demo.kafka;

import cn.hutool.core.date.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

/**
 * @author xuchunpeng 2020/5/13
 */
@Component
public class SnortTest11Producer {

    @Autowired
    private KafkaTemplate kafkaTemplate;

   // @Scheduled(cron = "*/3 * * * * ?")
    public void send() {
        // DateUtil.current(false)
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
        ListenableFuture listenableFuture = kafkaTemplate.send("snort_test11",value);
        SendResult sendResult = checkProRecord(listenableFuture);
        System.out.println(DateUtil.now());
    }

    @Scheduled(cron = "*/1 * * * * ?")
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
