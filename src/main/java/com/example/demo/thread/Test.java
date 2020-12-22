package com.example.demo.thread;

import cn.hutool.core.date.DateUtil;

import java.util.concurrent.ThreadPoolExecutor;

public class Test {

    private ThreadPoolExecutor threadPoolExecutor;

    public static void main(String[] args) {
        String startRowKey = String.join("-", "ovs", "projectId", DateUtil.date(System.currentTimeMillis()).toString());
        long AUTH_TIMESTAMP = 60 * 60 * 1_000L;
        System.out.println(AUTH_TIMESTAMP);
        System.out.println(DateUtil.date(System.currentTimeMillis()).toString());
        System.out.println(DateUtil.date(System.currentTimeMillis() + AUTH_TIMESTAMP).toString());
        System.out.println(DateUtil.date(System.currentTimeMillis() - 1000 * 60 * 30).toString());
    }
}
