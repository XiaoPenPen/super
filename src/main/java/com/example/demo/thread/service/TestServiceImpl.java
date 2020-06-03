package com.example.demo.thread.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author xuchunpeng 2020/6/2
 */
@Service
public class TestServiceImpl implements TestService{

    public static Integer num = 0;

    @Override
    @Async("testtest")
    public void test(Integer integer) {
        try {
            Thread.sleep(1);
            System.out.println(Thread.currentThread().getName() + "________________" + integer);
            num++;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
