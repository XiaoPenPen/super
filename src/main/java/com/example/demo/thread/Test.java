package com.example.demo.thread;

import java.util.concurrent.ThreadPoolExecutor;

public class Test{

    private ThreadPoolExecutor threadPoolExecutor;
    private static Integer num = 0;

//    public static void main(String[] args) {
//        String startRowKey = String.join("-", "ovs", "projectId", DateUtil.date(System.currentTimeMillis()).toString());
//        long AUTH_TIMESTAMP = 60 * 60 * 1_000L;
//        System.out.println(AUTH_TIMESTAMP);
//        System.out.println(DateUtil.date(System.currentTimeMillis()).toString());
//        System.out.println(DateUtil.date(System.currentTimeMillis() + AUTH_TIMESTAMP).toString());
//        System.out.println(DateUtil.date(System.currentTimeMillis() - 1000 * 60 * 30).toString());
//    }

    public synchronized static void setCache(){
        System.out.println("设置开始>>>>>>>>>");
        removeCache();
        try {
            Thread.sleep(1000);
            num = 666;
            System.out.println("设置完成<<<<<<<<<<<");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void removeCache(){
        num = 0;
    }

    public synchronized static void getCache(){
        System.out.println("getCache=" + num);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 50; i++) {
            Thread t1 = new Thread(Test::setCache);
            Thread t2 = new Thread(Test::getCache);
            t1.start();
            t2.start();
        }
    }


}
