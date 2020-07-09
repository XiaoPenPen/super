package com.example.demo;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.json.JSONUtil;
import com.example.demo.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.io.IOException;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.time.Duration;
import java.util.*;

public class Test {
    private static int i = 0;
    public static void main(String[] args) {
        /*String str = "{a:1}";
        String str2 = "[{a:1}]";*/
    /*    System.out.println(JSONUtil.parseObj(null));
        System.out.println(JSONUtil.parseArray(str2));*/

 /*       //创建Timer一个对象

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    i+=1;
                    System.out.println(i);
                    if (i == 5){
                        int b = i/0;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    timer.cancel();
                }

            }
        },  10,1000);// 毫秒

        System.out.println("执行结束");
        System.out.println("执行结束");
        System.out.println("执行结束");
        System.out.println("执行结束");
        System.out.println("执行结束");
        System.out.println("执行结束");
        System.out.println("执行结束");
        System.out.println("执行结束");
        System.out.println("执行结束");
        System.out.println("执行结束");
*/
        /*List list = new ArrayList<>();
        list.add(new User());
        list.add(new User());
        list.add(new User());
        User user = new User();
        user.setId(1);
        user.setName("Test");
        user.setTest(new User());*/
/*
        Set<Integer> set = new HashSet<>();
        set.add(i);
        i = 2;
        set.add(i);
        set.forEach(System.out::println);*/
/*
        User user = new User();
        Set<User> set = new HashSet<>();
        System.out.println(DateUtil.offsetSecond(DateUtil.parse("2020-04-29 01:26:00"),60));
        System.out.println(DateUtil.now());
        System.out.println(DateUtil.between(new Date(),DateUtil.offsetSecond(DateUtil.parse("2020-04-29 01:26:00"),60), DateUnit.SECOND, false));

*/

       /* List<String> list = Arrays.asList("123", "456111134");
        List<String> list2 = Arrays.asList("1222234", "5678");
        list.forEach(e ->{
            list2.forEach(x -> {
                if(x.length() >= 5){
                    return;
                }
                System.out.println(x);
            });
            System.out.println(e);
        });*/
        /*Set<User> set = new HashSet<>();
        User user = new User();
        User user2 = new User();
        User user3 = new User();
        user.setId(1);
        user2.setId(1);
        user.setName("Test");
        user2.setName("Test");
        set.add(user);
        set.add(user2);
        set.forEach(user1 -> {
            System.out.println(user1);
        });*/
        //getKafkaData();




    }

    public static void getKafkaData(){
        List list = new ArrayList();
        list.add("172.18.0.46:9092");
        list.add("172.18.0.47:9092");
        list.add("172.18.0.48:9092");
        Properties p = new Properties();
        p.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "172.18.0.46:9092,172.18.0.47:9092,172.18.0.48:9092");
        p.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        p.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        p.put(ConsumerConfig.GROUP_ID_CONFIG, "test_1111");
        //p.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, 5);

        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<>(p);
        // 订阅消息
        kafkaConsumer.subscribe(Collections.singletonList("snort_test11"));

        ConsumerRecords<String, String> records = kafkaConsumer.poll(Duration.ofMillis(50000));
        for (ConsumerRecord<String, String> record : records) {
            System.out.printf("-------" + record.topic() + "_" + record.value());
        }
    }
}
