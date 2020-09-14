package com.example.demo;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.Tailer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

public class Test {
    public static void main(String[] args) throws IOException, InvalidFormatException {

        /*String data = "{\\\"code\\\":1,\\\"data\\\":{\\\"id\\\":\\\"7a96fd11da3f492481e03b897c712846\\\",\\\"projectId\\\":\\\"f515f91afe9b4d069296f6b6e789f92a\\\",\\\"screenId\\\":1,\\\"name\\\":\\\"test0424\\\",\\\"height\\\":1080,\\\"width\\\":1920,\\\"creator\\\":\\\"bbb2ab89ec4847af9b283af49d704892\\\",\\\"createtime\\\":\\\"2020-04-2410:02:15\\\",\\\"components\\\":[{\\\"componentId\\\":64,\\\"screenConfigId\\\":\\\"7a96fd11da3f492481e03b897c712846\\\",\\\"name\\\":\\\"安全事件\\\",\\\"id\\\":\\\"07c596ec5d0843e0999494e3ebc4d172\\\",\\\"ifdisplay\\\":true,\\\"commonStyle\\\":{\\\"top\\\":604,\\\"left\\\":14,\\\"bottom\\\":340,\\\"width\\\":400,\\\"fontSize\\\":14,\\\"right\\\":760,\\\"fontColor\\\":\\\"rgba(46,99,154,1)\\\",\\\"zIndex\\\":1,\\\"height\\\":400},\\\"type\\\":\\\"hw-security\\\",\\\"projectId\\\":\\\"f515f91afe9b4d069296f6b6e789f92a\\\",\\\"pirture\\\":\\\"htgroup1M00000FooYBAF6gXfmAJM_KAAAUrgF80F4759.png\\\",\\\"events\\\":[]},{\\\"componentId\\\":5,\\\"screenConfigId\\\":\\\"7a96fd11da3f492481e03b897c712846\\\",\\\"name\\\":\\\"虚拟机网络流量\\\",\\\"id\\\":\\\"25548d3cf61845f59da92c06f4f8ce72\\\",\\\"ifdisplay\\\":true,\\\"commonStyle\\\":{\\\"top\\\":143,\\\"left\\\":1403,\\\"bottom\\\":650,\\\"width\\\":500,\\\"fontSize\\\":14,\\\"right\\\":20,\\\"fontColor\\\":\\\"rgba(45,127,213,1)\\\",\\\"zIndex\\\":10,\\\"height\\\":350},\\\"type\\\":\\\"bz-neworkFlow\\\",\\\"projectId\\\":\\\"f515f91afe9b4d069296f6b6e789f92a\\\",\\\"dataSource\\\":{\\\"id\\\":8,\\\"name\\\":\\\"虚拟机连接信息\\\",\\\"type\\\":\\\"api\\\",\\\"url\\\":\\\"http:172.18.0.21:8999overview_connection_num{projectId}20\\\",\\\"method\\\":\\\"GET\\\",\\\"period\\\":10,\\\"data\\\":{}},\\\"pirture\\\":\\\"dfshtgroup1M000005rBIAL11wtN6AWEbbAACdq-g5_F8930.png\\\",\\\"events\\\":[{\\\"type\\\":\\\"test-hidden\\\",\\\"name\\\":\\\"测试-隐藏\\\",\\\"params\\\":{}},{\\\"type\\\":\\\"test-show\\\",\\\"name\\\":\\\"测试-显示\\\",\\\"params\\\":{}}]},{\\\"componentId\\\":4,\\\"screenConfigId\\\":\\\"7a96fd11da3f492481e03b897c712846\\\",\\\"name\\\":\\\"大屏标题01\\\",\\\"id\\\":\\\"6037d1e71143417dbe615ba9bee7ea4a\\\",\\\"ifdisplay\\\":true,\\\"commonStyle\\\":{\\\"top\\\":0,\\\"left\\\":58,\\\"bottom\\\":0,\\\"width\\\":1800,\\\"fontSize\\\":32,\\\"right\\\":0,\\\"fontColor\\\":\\\"rgba(18,112,210,1)\\\",\\\"zIndex\\\":10,\\\"height\\\":60},\\\"type\\\":\\\"bz-pageTitle1\\\",\\\"projectId\\\":\\\"f515f91afe9b4d069296f6b6e789f92a\\\",\\\"pirture\\\":\\\"dfshtgroup1M000000rBIAL11bZaKADnsyAAJXjL-T1Yg380.png\\\",\\\"events\\\":[{\\\"type\\\":\\\"test-hidden\\\",\\\"name\\\":\\\"测试-隐藏\\\",\\\"params\\\":{}},{\\\"type\\\":\\\"test-show\\\",\\\"name\\\":\\\"测试-显示\\\",\\\"params\\\":{}}]},{\\\"componentId\\\":60,\\\"screenConfigId\\\":\\\"7a96fd11da3f492481e03b897c712846\\\",\\\"name\\\":\\\"3Dtopo\\\",\\\"id\\\":\\\"6ec3616375d6415c87c5664a6480d0c7\\\",\\\"ifdisplay\\\":true,\\\"commonStyle\\\":{\\\"top\\\":0,\\\"left\\\":0,\\\"bottom\\\":0,\\\"width\\\":1920,\\\"right\\\":0,\\\"zIndex\\\":0,\\\"height\\\":1080},\\\"type\\\":\\\"bz-topo\\\",\\\"projectId\\\":\\\"f515f91afe9b4d069296f6b6e789f92a\\\",\\\"dataSource\\\":{\\\"id\\\":7,\\\"name\\\":\\\"3d拓扑结构\\\",\\\"type\\\":\\\"api\\\",\\\"url\\\":\\\"http:172.18.100.2topotopoprojects{projectId}auto_layout_3D\\\",\\\"method\\\":\\\"GET\\\",\\\"data\\\":{}},\\\"pirture\\\":\\\"dfshtgroup1M00000FrBIAL16QfuqAbRN8AAASTkUZcus126.png\\\",\\\"events\\\":[]},{\\\"componentId\\\":63,\\\"screenConfigId\\\":\\\"7a96fd11da3f492481e03b897c712846\\\",\\\"name\\\":\\\"时钟\\\",\\\"id\\\":\\\"70de5a4f9e4341be8afce9196202e0bf\\\",\\\"ifdisplay\\\":true,\\\"commonStyle\\\":{\\\"top\\\":50,\\\"left\\\":1600,\\\"bottom\\\":980,\\\"width\\\":300,\\\"fontSize\\\":32,\\\"right\\\":20,\\\"fontColor\\\":\\\"rgba(43,128,217,1)\\\",\\\"zIndex\\\":1,\\\"height\\\":60},\\\"type\\\":\\\"bz-clock\\\",\\\"projectId\\\":\\\"f515f91afe9b4d069296f6b6e789f92a\\\",\\\"pirture\\\":\\\"dfshtgroup1M00000FoYYBAF6ivC6ASWoMAAFY-gilTbg091.png\\\",\\\"events\\\":[]},{\\\"componentId\\\":1,\\\"screenConfigId\\\":\\\"7a96fd11da3f492481e03b897c712846\\\",\\\"name\\\":\\\"设备统计\\\",\\\"id\\\":\\\"853c9978d7814113bebafbea048bab31\\\",\\\"ifdisplay\\\":true,\\\"commonStyle\\\":{\\\"top\\\":52,\\\"left\\\":3,\\\"bottom\\\":550,\\\"width\\\":613,\\\"right\\\":1400,\\\"zIndex\\\":10,\\\"height\\\":521},\\\"type\\\":\\\"bz-device\\\",\\\"projectId\\\":\\\"f515f91afe9b4d069296f6b6e789f92a\\\",\\\"pirture\\\":\\\"dfshtgroup1M000005rBIAMF1ws6-AE9QRAADVgx4Gtu8246.png\\\",\\\"events\\\":[{\\\"type\\\":\\\"test-show\\\",\\\"name\\\":\\\"测试-显示\\\",\\\"params\\\":{}}]}]},\\\"message\\\":\\\"SUCCESS\\\"}\n";
        System.out.println(data.replaceAll(" ","").replaceAll("","").replaceAll("\\\\",""));

        System.out.println(DateUtil.date(1589424399000L));
        Object obj  = new User();
        System.out.println(obj instanceof String);*/
        /*String start = "2020-05-24 10:30:31";
        String end = "2020-05-25 11:30:32";
        long between = DateUtil.between(DateUtil.parse(start), DateUtil.parse(end), DateUnit.MS);
        String formatBetween = DateUtil.formatBetween(between, BetweenFormater.Level.SECOND);
        System.out.println(formatBetween);
        String dateStr1 = "2017-04-01 23:33:29";
        Date date1 = DateUtil.parse(dateStr1);

        String dateStr2 = "2017-04-01 23:33:23";
        Date date2 = DateUtil.parse(dateStr2);

        System.out.println(DateUtil.between(date1, date2, DateUnit.SECOND, false));*/
//        boolean b = (DateUtil.between(new Date(),DateUtil.offsetDay(DateUtil.parse("2020-05-20 23:33:23"), 5), DateUnit.DAY, false) <= 0);
//        System.out.println((DateUtil.between(new Date(),DateUtil.offsetDay(DateUtil.parse("2020-05-20 23:33:23"), 5), DateUnit.DAY, false)));
//        System.out.println(DateUtil.offsetDay(DateUtil.parse("2020-05-20 23:33:23"), 5));
//        System.out.println(b);
        /*Map<String,Object> map = new HashMap<>();
        map.put("user", new User());
        System.out.println(map.get("user").toString());

        User user = (User) map.get("user");

        user.setId(1);
        System.out.println(map.get("user").toString());*/
        Tailer tailer = new Tailer(FileUtil.file("e://data/1.txt"), Tailer.CONSOLE_HANDLER, 2);
        tailer.start();
    }

    public static void delete(File file) {
        if (!file.exists()) return;

        if (file.isFile() || file.list() == null) {
            file.delete();
            System.out.println("删除了" + file.getName());
        } else {
            File[] files = file.listFiles();
            for (File a : files) {
                delete(a);
            }
            file.delete();
            System.out.println("删除了" + file.getName());
        }
    }
    public static void test(){
        Properties p = new Properties();
        List<String> reslutList = new ArrayList<>();
        p.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "47.244.107.125:9092");
        p.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        p.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        p.put(ConsumerConfig.GROUP_ID_CONFIG, "screen-control-demo");
        p.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");

        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<>(p);
        // 订阅消息
        kafkaConsumer.subscribe(Collections.singletonList("snort_test11"));
        ConsumerRecords<String, String> records = kafkaConsumer.poll(Duration.ofMillis(2000));// 超时时间 2s
        for (ConsumerRecord<String, String> record : records) {
            reslutList.add(record.value());
        }
        kafkaConsumer.commitSync();
    }


}
