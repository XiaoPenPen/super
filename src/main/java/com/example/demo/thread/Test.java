package com.example.demo.thread;

import org.springframework.scheduling.annotation.Async;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author xuchunpeng 2020/6/2
 */
public class Test {

    private ThreadPoolExecutor threadPoolExecutor;

    public static void main(String[] args) {
        Test test = new Test();

    }

    @Async
    public void test(Integer i){
        System.out.println(Thread.currentThread().getName() + "________________" + i);
    }

}
