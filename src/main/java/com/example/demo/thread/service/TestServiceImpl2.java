package com.example.demo.thread.service;

import com.example.demo.common.SpringContextHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author xuchunpeng 2020/6/2
 */
@Service
public class TestServiceImpl2 {

    public static Integer num = 0;

    public void test(Integer integer) {
        TestServiceImpl2 testService = SpringContextHelper.getBean(TestServiceImpl2.class);
        testService.test2(integer);
    }

    @Async("testtest")
    public void test2(Integer integer) {
        try {
            Thread.sleep(1);
            System.out.println(Thread.currentThread().getName() + "________________" + integer);
            num++;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
